package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The WithdrawCommand class implements the command to withdraw an amount from an account.
 */
public class WithdrawCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BankFacade bankFacade;
    private String phoneNumber;
    private double amount;

    public WithdrawCommand(BankFacade bankFacade, String phoneNumber, double amount) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    @Override
    public String execute() {
        return bankFacade.withdrawFromAccount(phoneNumber, amount);
    }
}
