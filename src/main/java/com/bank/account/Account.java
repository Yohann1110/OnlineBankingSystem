package com.bank.account;

import java.io.Serializable;

/**
 * The Account class represents a bank account.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private double balance;

    /**
     * Constructor to initialize an account with the given phone number and balance.
     *
     * @param phoneNumber The phone number associated with the account.
     * @param balance The initial balance of the account.
     */
    public Account(String phoneNumber, double balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        updateBalance(amount);
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to withdraw.
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            updateBalance(-amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    /**
     * Displays the account details.
     */
    public void display() {
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Available Balance: " + balance);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
