package com.bank.account;

import java.io.Serializable;

/**
 * The AccountDecorator class is an abstract class that extends Account and contains a reference
 * to another Account object. It delegates calls to the wrapped account object, allowing additional
 * behavior to be added.
 */
public abstract class AccountDecorator extends Account implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Account decoratedAccount;

    public AccountDecorator(Account decoratedAccount) {
        super(decoratedAccount.getPhoneNumber(), decoratedAccount.getBalance());
        this.decoratedAccount = decoratedAccount;
    }

    @Override
    public void deposit(double amount) {
        decoratedAccount.deposit(amount); // This will call updateBalance within the decorated account
    }

    @Override
    public void withdraw(double amount) {
        decoratedAccount.withdraw(amount); // This will call updateBalance within the decorated account
    }

    @Override
    public void display() {
        decoratedAccount.display();
    }

    @Override
    public double getBalance() {
        return decoratedAccount.getBalance();
    }

    @Override
    public void updateBalance(double amount) {
        decoratedAccount.updateBalance(amount);
    }
}
