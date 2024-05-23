package com.bank.account;

public class PremiumAccount extends AccountDecorator {
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
