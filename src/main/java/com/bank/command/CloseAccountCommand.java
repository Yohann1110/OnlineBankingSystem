package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The CloseAccountCommand class implements the Command interface to close an account.
 */
public class CloseAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to close the account.
     * @param accountId The ID of the account.
     */
    public CloseAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    /**
     * Executes the command to close the account.
     *
     * @return A message indicating the result of the closure.
     */
    @Override
    public String execute() {
        return bankFacade.closeAccount(accountId);
    }
}
