package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The DepositCommand class implements the command to deposit an amount into an account.
 */
public class DepositCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BankFacade bankFacade;
    private String phoneNumber;
    private double amount;

    public DepositCommand(BankFacade bankFacade, String phoneNumber, double amount) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    @Override
    public String execute() {
        return bankFacade.depositToAccount(phoneNumber, amount);
    }
}
