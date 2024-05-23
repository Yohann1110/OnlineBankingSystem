package com.bank.account;

import com.bank.state.AccountState;
import com.bank.state.ActiveState;
import com.bank.state.ClosedState;
import com.bank.state.SuspendedState;

public class Account {
    private String accountId;
    private double balance;
    private AccountState state;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.state = new ActiveState(this);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountState getState() {
        return state;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public void deposit(double amount) {
        state.deposit(amount);
    }

    public void withdraw(double amount) {
        state.withdraw(amount);
    }

    public void display() {
        state.display();
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void suspend() {
        setState(new SuspendedState(this));
    }

    public void close() {
        setState(new ClosedState(this));
    }

    public void activate() {
        setState(new ActiveState(this));
    }
}
