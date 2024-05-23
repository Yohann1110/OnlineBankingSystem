package com.bank.command;

import com.bank.facade.BankFacade;

public class SuspendAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    public SuspendAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    @Override
    public String execute() {
        return bankFacade.suspendAccount(accountId);
    }
}
