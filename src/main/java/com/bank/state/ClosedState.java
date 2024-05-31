package com.bank.state;

import com.bank.account.Account;

/**
 * The ClosedState class represents the closed state of an account.
 */
public class ClosedState implements AccountState {
    private static final long serialVersionUID = 1L;
    private Account account;

    public ClosedState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Cannot deposit to a closed account");
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from a closed account");
    }

    @Override
    public void display() {
        System.out.println("Account is closed");
    }

    @Override
    public String getDescription() {
        return "closed";
    }
}
