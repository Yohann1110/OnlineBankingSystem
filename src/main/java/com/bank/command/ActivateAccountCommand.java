package com.bank.command;

import com.bank.facade.BankFacade;

public class ActivateAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    public ActivateAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    @Override
    public String execute() {
        return bankFacade.activateAccount(accountId);
    }
}