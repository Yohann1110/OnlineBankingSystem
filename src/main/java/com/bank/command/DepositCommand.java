package com.bank.command;

import com.bank.facade.BankFacade;

public class DepositCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private double amount;

    public DepositCommand(BankFacade bankFacade, String accountId, double amount) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public String execute() {
        return bankFacade.depositToAccount(accountId, amount);
    }
}
