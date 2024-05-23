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

public class BankFacade {
    private Map<String, Account> accounts;
    private List<Transaction> transactions;

    public BankFacade(List<Transaction> transactions) {
        this.accounts = new HashMap<>();
        this.transactions = transactions;
    }

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

    public String depositToAccount(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.deposit(amount);
        return "Deposited " + amount + " to account " + accountId;
    }

    public String withdrawFromAccount(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.withdraw(amount);
        return "Withdrew " + amount + " from account " + accountId;
    }

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

    public String suspendAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.suspend();
        return "Account " + accountId + " is now suspended.";
    }

    public String closeAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.close();
        return "Account " + accountId + " is now closed.";
    }

    public String activateAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }
        account.activate();
        return "Account " + accountId + " is now active.";
    }
}
