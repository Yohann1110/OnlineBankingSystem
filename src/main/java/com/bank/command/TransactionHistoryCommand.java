package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The TransactionHistoryCommand class implements the command to retrieve the transaction history of an account.
 */
public class TransactionHistoryCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BankFacade bankFacade;
    private String phoneNumber;

    public TransactionHistoryCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String execute() {
        return bankFacade.getTransactionHistory(phoneNumber);
    }
}
