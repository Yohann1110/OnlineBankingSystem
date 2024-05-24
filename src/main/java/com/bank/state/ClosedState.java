package com.bank.state;

import com.bank.account.Account;

/**
 * The ClosedState class represents the closed state of an account.
 * It defines behavior specific to a closed account.
 */
public class ClosedState implements AccountState {
    private Account account;

    public ClosedState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Account is closed. Cannot deposit.");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Account is closed. Cannot withdraw.");
    }

    @Override
    public void display() {
        System.out.println("Phone Number: " + account.getPhoneNumber() + ", Balance: " + account.getBalance() + " (closed)");
    }
}
