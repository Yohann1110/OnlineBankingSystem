package com.bank.account;

/**
 * The PremiumAccount class is a concrete decorator that extends AccountDecorator.
 * It adds new functionalities (e.g., premium services) to the Account objects dynamically.
 */
public class PremiumAccount extends AccountDecorator {
    private static final long serialVersionUID = 1L;

    public PremiumAccount(Account decoratedAccount) {
        super(decoratedAccount);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        System.out.println("Premium account deposit: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        System.out.println("Premium account withdrawal: " + amount);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("This is a premium account.");
    }
}
