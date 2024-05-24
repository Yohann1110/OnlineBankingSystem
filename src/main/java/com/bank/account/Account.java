package com.bank.account;

import com.bank.state.AccountState;
import com.bank.state.ActiveState;
import com.bank.state.ClosedState;
import com.bank.state.SuspendedState;

/**
 * The Account class represents a bank account. It maintains the account's state and balance,
 * and delegates state-specific behavior to the current state object.
 */
public class Account {
    private String phoneNumber;
    private double balance;
    private AccountState state;

    /**
     * Constructor to initialize an account with the given phone number and balance.
     *
     * @param phoneNumber The phone number associated with the account.
     * @param balance The initial balance of the account.
     */
    public Account(String phoneNumber, double balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.state = new ActiveState(this); // Default state is active
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    /**
     * Deposits the specified amount into the account by delegating to the state object.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        state.deposit(amount);
    }

    /**
     * Withdraws the specified amount from the account by delegating to the state object.
     *
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        state.withdraw(amount);
    }

    /**
     * Displays the account details by delegating to the state object.
     */
    public void display() {
        state.display();
    }

    /**
     * Updates the account balance by the specified amount.
     *
     * @param amount The amount to update the balance by.
     */
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Suspends the account by changing its state to suspended.
     */
    public void suspend() {
        setState(new SuspendedState(this));
    }

    /**
     * Closes the account by changing its state to closed.
     */
    public void close() {
        setState(new ClosedState(this));
    }

    /**
     * Activates the account by changing its state to active.
     */
    public void activate() {
        setState(new ActiveState(this));
    }
}
