package com.bank.account;

public abstract class AccountDecorator extends Account {
    protected Account decoratedAccount;

    public AccountDecorator(Account decoratedAccount) {
        super(decoratedAccount.getAccountId(), decoratedAccount.getBalance());
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
