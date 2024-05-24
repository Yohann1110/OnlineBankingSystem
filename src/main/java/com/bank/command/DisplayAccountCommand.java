package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The DisplayAccountCommand class implements the Command interface to display account details.
 */
public class DisplayAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to display the account.
     * @param accountId The ID of the account.
     */
    public DisplayAccountCommand(BankFacade bankFacade, String accountId) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
    }

    /**
     * Executes the command to display the account details.
     *
     * @return The account details as a string.
     */
    @Override
    public String execute() {
        return bankFacade.displayAccount(accountId);
    }
}
