package com.bank.account;

/**
 * The AccountDecorator class is an abstract class that extends Account and contains a reference
 * to another Account object. It delegates calls to the wrapped account object, allowing additional
 * behavior to be added.
 */
public abstract class AccountDecorator extends Account {
    protected Account decoratedAccount;

    public AccountDecorator(Account decoratedAccount) {
        super(decoratedAccount.getPhoneNumber(), decoratedAccount.getBalance());
        this.decoratedAccount = decoratedAccount;
    }

    @Override
    public void deposit(double amount) {
        decoratedAccount.deposit(amount);
    }

    @Override
    public void withdraw(double amount) {
        decoratedAccount.withdraw(amount);
    }

    @Override
    public void display() {
        decoratedAccount.display();
    }
}
