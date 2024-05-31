package com.bank.state;

import com.bank.account.Account;

/**
 * The ActiveState class represents the active state of an account.
 */
public class ActiveState implements AccountState {
    private static final long serialVersionUID = 1L;
    private Account account;

    public ActiveState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        account.updateBalance(amount);
    }

    @Override
    public void withdraw(double amount) {
        if (account.getBalance() >= amount) {
            account.updateBalance(-amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public void display() {
        System.out.println("Account is active");
    }

    @Override
    public String getDescription() {
        return "active";
    }
}
