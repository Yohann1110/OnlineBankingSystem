package com.bank.command;

import com.bank.facade.BankFacade;

public class CreateAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private String type;

    public CreateAccountCommand(BankFacade bankFacade, String accountId, String type) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.type = type;
    }

    @Override
    public String execute() {
        return bankFacade.createAccount(accountId, type);
    }
}
