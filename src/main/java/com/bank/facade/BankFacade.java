package com.bank.facade;

import com.bank.account.Account;
import com.bank.account.PremiumAccount;
import com.bank.account.RewardsAccount;
import com.bank.transaction.Transaction;

import java.io.*;
import java.util.HashMap;
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
        this.transactions = transactions;
        loadData();
    }

    /**
     * Creates a new account of the specified type.
     *
     * @param phoneNumber The phone number associated with the account to be created.
     * @param type The type of the account (e.g., PREMIUM, REWARDS).
     * @return A message indicating the result of the account creation.
     */
    public String createAccount(String phoneNumber, String type) {
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
    public String depositToAccount(String phoneNumber, double amount) {
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.deposit(amount);
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
    public String withdrawFromAccount(String phoneNumber, double amount) {
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.withdraw(amount);
        saveData();
        return "Withdrew " + amount + " from account " + phoneNumber;
    }

    /**
     * Displays the details of the given account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return The account details as a string.
     */
    public String displayAccount(String phoneNumber) {
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
        String accountDetails = baos.toString();
        return accountDetails + "Available Balance: " + account.getBalance();
    }

    /**
     * Suspends the specified account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return A message indicating the result of the suspension.
     */
    public String suspendAccount(String phoneNumber) {
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.suspend();
        saveData();
        return "Account " + phoneNumber + " is now suspended.";
    }

    /**
     * Closes the specified account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return A message indicating the result of the closure.
     */
    public String closeAccount(String phoneNumber) {
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.close();
        saveData();
        return "Account " + phoneNumber + " is now closed.";
    }

    /**
     * Activates the specified account.
     *
     * @param phoneNumber The phone number associated with the account.
     * @return A message indicating the result of the activation.
     */
    public String activateAccount(String phoneNumber) {
        Account account = accounts.get(phoneNumber);
        if (account == null) {
            return "Account not found: " + phoneNumber;
        }
        account.activate();
        saveData();
        return "Account " + phoneNumber + " is now active.";
    }

    /**
     * Transfers the specified amount from one account to another.
     *
     * @param fromPhoneNumber The phone number associated with the account to transfer money from.
     * @param toPhoneNumber The phone number associated with the account to transfer money to.
     * @param amount The amount to transfer.
     * @return A message indicating the result of the transfer.
     */
    public String transfer(String fromPhoneNumber, String toPhoneNumber, double amount) {
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
        saveData();

        return "Transferred " + amount + " from " + fromPhoneNumber + " to " + toPhoneNumber;
    }

    /**
     * Saves the current state of accounts and transactions to a file.
     */
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the state of accounts and transactions from a file.
     */
    private void loadData() {
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
}
