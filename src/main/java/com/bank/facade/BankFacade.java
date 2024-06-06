package com.bank.facade;

import com.bank.account.Account;
import com.bank.account.PremiumAccount;
import com.bank.account.RewardsAccount;
import com.bank.transaction.Transaction;
import com.bank.transaction.TransferTransaction;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The BankFacade class provides a simplified interface to various banking operations.
 * It hides the complexities of the subsystem and allows the client to interact with it easily.
 */
public class BankFacade implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Account> accounts;
    private List<Transaction> transactions;
    private static final String DATA_FILE = "bank_data.ser";

    /**
     * Constructor to initialize the BankFacade with a list of transactions.
     *
     * @param transactions List of transactions to be managed by the facade.
     */
    public BankFacade(List<Transaction> transactions) {
        // Initialize the accounts map and transactions list
        this.accounts = new HashMap<>();
        this.transactions = transactions != null ? transactions : new LinkedList<>();
        loadData();
    }

    /**
     * Creates a new account of the specified type.
     *
     * @param phoneNumber The phone number associated with the account to be created.
     * @param type The type of the account (e.g., PREMIUM, REWARDS).
     * @return A message indicating the result of the account creation.
     */
    public synchronized String createAccount(String phoneNumber, String type) {
        loadData();
        if (accounts.containsKey(phoneNumber)) {
            return "An account with phone number " + phoneNumber + " already exists.";
        }

        Account account;
        if (type.equals("PREMIUM")) {
            account = new PremiumAccount(new Account(phoneNumber, 0));
        } else if (type.equals("REWARDS")) {
            account = new RewardsAccount(new Account(phoneNumber, 0));
        } else {
            return "Unknown account type.";
        }
        accounts.put(phoneNumber, account);
        saveData();
        return "Account created: " + phoneNumber + " of type " + type;
    }

    /**
     * Deposits the specified amount into the given account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @param amount The amount to deposit.
     * @return A message indicating the result of the deposit.
     */
    public synchronized String depositToAccount(String phoneNumber, double amount) {
        loadData();
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.deposit(amount);
        transactions.add(new Transaction(generateTransactionId(), phoneNumber, amount, getCurrentDate()));
        saveData();
        return "Deposited " + amount + " to account " + phoneNumber;
    }

    /**
     * Withdraws the specified amount from the given account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @param amount The amount to withdraw.
     * @return A message indicating the result of the withdrawal.
     */
    public synchronized String withdrawFromAccount(String phoneNumber, double amount) {
        loadData();
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.withdraw(amount);
        transactions.add(new Transaction(generateTransactionId(), phoneNumber, amount, getCurrentDate()));
        saveData();
        return "Withdrew " + amount + " from account " + phoneNumber;
    }

    /**
     * Displays the details of the given account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return The account details as a string.
     */
    public synchronized String displayAccount(String phoneNumber) {
        loadData();
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        account.display();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    /**
     * Transfers the specified amount from one account to another.
     *
     * @param fromPhoneNumber The phone number associated with the account to transfer money from.
     * @param toPhoneNumber The phone number associated with the account to transfer money to.
     * @param amount The amount to transfer.
     * @return A message indicating the result of the transfer.
     */
    public synchronized String transfer(String fromPhoneNumber, String toPhoneNumber, double amount) {
        loadData();
        Account fromAccount = accounts.get(fromPhoneNumber);
        Account toAccount = accounts.get(toPhoneNumber);

        if (fromAccount == null) {
            return "Source account not found: " + fromPhoneNumber;
        }
        if (toAccount == null) {
            return "Destination account not found: " + toPhoneNumber;
        }
        if (fromAccount.getBalance() < amount) {
            return "Insufficient balance in source account: " + fromPhoneNumber;
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        transactions.add(new TransferTransaction(generateTransactionId(), fromPhoneNumber, toPhoneNumber, amount, getCurrentDate()));
        saveData();
        return "Transferred " + amount + " from " + fromPhoneNumber + " to " + toPhoneNumber;
    }

    /**
     * Generates a transaction history for the given account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return The transaction history as a string.
     */
    public synchronized String getTransactionHistory(String phoneNumber) {
        loadData();
        StringBuilder history = new StringBuilder();
        for (Transaction transaction : transactions) {
            if (transaction.getFromPhoneNumber().equals(phoneNumber) || transaction.getToPhoneNumber().equals(phoneNumber)) {
                history.append(transaction.toString()).append("\n");
            }
        }
        return history.toString();
    }

    /**
     * Saves the current state of accounts and transactions to a file.
     */
    private synchronized void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the state of accounts and transactions from a file.
     */
    private synchronized void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            BankFacade loadedData = (BankFacade) ois.readObject();
            this.accounts = loadedData.accounts;
            this.transactions = loadedData.transactions;
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, starting with an empty state.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }

    private String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }
}
