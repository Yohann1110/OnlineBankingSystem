package com.bank.state;

import com.bank.account.Account;

/**
 * The SuspendedState class represents the suspended state of an account.
 * It defines behavior specific to a suspended account.
 */
public class SuspendedState implements AccountState {
    private Account account;

    public SuspendedState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Account is suspended. Cannot deposit.");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Account is suspended. Cannot withdraw.");
    }

    @Override
    public void display() {
        System.out.println("Account ID: " + account.getAccountId() + ", Balance: " + account.getBalance() + " (suspended)");
    }
}
