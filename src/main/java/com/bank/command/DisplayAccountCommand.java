package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The DisplayAccountCommand class implements the command to display an account's details.
 */
public class DisplayAccountCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BankFacade bankFacade;
    private String phoneNumber;

    public DisplayAccountCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String execute() {
        return bankFacade.displayAccount(phoneNumber);
    }
}
