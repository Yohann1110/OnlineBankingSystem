package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The CreateAccountCommand class implements the Command interface to create a new bank account.
 */
public class CreateAccountCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private String type;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to create the account.
     * @param accountId The ID of the account to be created.
     * @param type The type of the account (e.g., PREMIUM, REWARDS).
     */
    public CreateAccountCommand(BankFacade bankFacade, String accountId, String type) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.type = type;
    }

    /**
     * Executes the command to create a new account.
     *
     * @return A message indicating the result of the account creation.
     */
    @Override
    public String execute() {
        return bankFacade.createAccount(accountId, type);
    }
}
