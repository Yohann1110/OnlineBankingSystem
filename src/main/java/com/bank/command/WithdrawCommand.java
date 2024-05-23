package com.bank.command;

import com.bank.facade.BankFacade;

public class WithdrawCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private double amount;

    public WithdrawCommand(BankFacade bankFacade, String accountId, double amount) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public String execute() {
        return bankFacade.withdrawFromAccount(accountId, amount);
    }
}
