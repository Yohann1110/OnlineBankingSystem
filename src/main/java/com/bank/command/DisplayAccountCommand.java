package com.bank.command;

import com.bank.facade.BankFacade;

public class DisplayAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    public DisplayAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    @Override
    public String execute() {
        return bankFacade.displayAccount(accountId);
    }
}
