package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The SuspendAccountCommand class implements the Command interface to suspend an account.
 */
public class SuspendAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to suspend the account.
     * @param accountId The ID of the account.
     */
    public SuspendAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    /**
     * Executes the command to suspend the account.
     *
     * @return A message indicating the result of the suspension.
     */
    @Override
    public String execute() {
        return bankFacade.suspendAccount(accountId);
    }
}
