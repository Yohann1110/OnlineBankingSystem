package com.bank.command;

import com.bank.facade.BankFacade;

public class CloseAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    public CloseAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    @Override
    public String execute() {
        return bankFacade.closeAccount(accountId);
    }
}
