package com.bank.state;

import com.bank.account.Account;

public class ActiveState implements AccountState {
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
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void display() {
        System.out.println("Account ID: " + account.getAccountId() + ", Balance: " + account.getBalance());
    }
}
