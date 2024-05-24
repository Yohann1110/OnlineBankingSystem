package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The ActivateAccountCommand class implements the Command interface to activate an account.
 */
public class ActivateAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to activate the account.
     * @param accountId The ID of the account.
     */
    public ActivateAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    /**
     * Executes the command to activate the account.
     *
     * @return A message indicating the result of the activation.
     */
    @Override
    public String execute() {
        return bankFacade.activateAccount(accountId);
    }
}
