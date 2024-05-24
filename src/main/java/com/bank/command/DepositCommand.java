package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The DepositCommand class implements the Command interface to deposit an amount into an account.
 */
public class DepositCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private double amount;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to perform the deposit.
     * @param accountId The ID of the account.
     * @param amount The amount to deposit.
     */
    public DepositCommand(BankFacade bankFacade, String accountId, double amount) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.amount = amount;
    }

    /**
     * Executes the command to deposit the amount into the account.
     *
     * @return A message indicating the result of the deposit.
     */
    @Override
    public String execute() {
        return bankFacade.depositToAccount(accountId, amount);
    }
}
