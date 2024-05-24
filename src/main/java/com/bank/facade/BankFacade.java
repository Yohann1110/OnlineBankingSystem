package com.bank.facade;

import com.bank.account.Account;
import com.bank.account.PremiumAccount;
import com.bank.account.RewardsAccount;
import com.bank.transaction.Transaction;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The BankFacade class provides a simplified interface to various banking operations.
 * It hides the complexities of the subsystem and allows the client to interact with it easily.
 */
public class BankFacade {
    private Map<String, Account> accounts;
    private List<Transaction> transactions;

    /**
     * Constructor to initialize the BankFacade with a list of transactions.
     *
     * @param transactions List of transactions to be managed by the facade.
     */
    public BankFacade(List<Transaction> transactions) {
        // Initialize the accounts map and transactions list
        this.accounts = new HashMap<>();
        this.transactions = transactions;
    }

    /**
     * Creates a new account of the specified type.
     *
     * @param accountId The ID of the account to be created.
     * @param type The type of the account (e.g., PREMIUM, REWARDS).
     * @return A message indicating the result of the account creation.
     */
    public String createAccount(String accountId, String type) {
        Account account;
        if (type.equals("PREMIUM")) {
            account = new PremiumAccount(new Account(accountId, 0));
        } else if (type.equals("REWARDS")) {
            account = new RewardsAccount(new Account(accountId, 0));
        } else {
            return "Unknown account type.";
        }
        accounts.put(accountId, account);
        return "Account created: " + accountId + " of type " + type;
    }

    /**
     * Deposits the specified amount into the given account.
     *
     * @param accountId The ID of the account.
     * @param amount The amount to deposit.
     * @return A message indicating the result of the deposit.
     */
    public String depositToAccount(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.deposit(amount);
        return "Deposited " + amount + " to account " + accountId;
    }

    /**
     * Withdraws the specified amount from the given account.
     *
     * @param accountId The ID of the account.
     * @param amount The amount to withdraw.
     * @return A message indicating the result of the withdrawal.
     */
    public String withdrawFromAccount(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.withdraw(amount);
        return "Withdrew " + amount + " from account " + accountId;
    }

    /**
     * Displays the details of the given account.
     *
     * @param accountId The ID of the account.
     * @return The account details as a string.
     */
    public String displayAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
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
     * Suspends the specified account.
     *
     * @param accountId The ID of the account.
     * @return A message indicating the result of the suspension.
     */
    public String suspendAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.suspend();
        return "Account " + accountId + " is now suspended.";
    }

    /**
     * Closes the specified account.
     *
     * @param accountId The ID of the account.
     * @return A message indicating the result of the closure.
     */
    public String closeAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.close();
        return "Account " + accountId + " is now closed.";
    }

    /**
     * Activates the specified account.
     *
     * @param accountId The ID of the account.
     * @return A message indicating the result of the activation.
     */
    public String activateAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.activate();
        return "Account " + accountId + " is now active.";
    }
}
