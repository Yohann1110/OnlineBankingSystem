package com.bank.state;

import com.bank.account.Account;

/**
 * The SuspendedState class represents the suspended state of an account.
 */
public class SuspendedState implements AccountState {
    private static final long serialVersionUID = 1L;
    private Account account;

    public SuspendedState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Cannot deposit to a suspended account");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from a suspended account");
    }

    @Override
    public void display() {
        System.out.println("Account is suspended");
    }

    @Override
    public String getDescription() {
        return "suspended";
    }
}
