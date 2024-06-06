package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The CreateAccountCommand class implements the command to create a new account.
 */
public class CreateAccountCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BankFacade bankFacade;
    private String phoneNumber;
    private String accountType;

    public CreateAccountCommand(BankFacade bankFacade, String phoneNumber, String accountType) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
    }

    @Override
    public String execute() {
        return bankFacade.createAccount(phoneNumber, accountType);
    }
}
