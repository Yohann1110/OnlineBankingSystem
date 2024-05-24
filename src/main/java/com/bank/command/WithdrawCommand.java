package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The WithdrawCommand class implements the Command interface to withdraw an amount from an account.
 */
public class WithdrawCommand implements Command {
    private BankFacade bankFacade;
    private String accountId;
    private double amount;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to perform the withdrawal.
     * @param accountId The ID of the account.
     * @param amount The amount to withdraw.
     */
    public WithdrawCommand(BankFacade bankFacade, String accountId, double amount) {
        this.bankFacade = bankFacade;
        this.accountId = accountId;
        this.amount = amount;
    }

    /**
     * Executes the command to withdraw the amount from the account.
     *
     * @return A message indicating the result of the withdrawal.
     */
    @Override
    public String execute() {
        return bankFacade.withdrawFromAccount(accountId, amount);
    }
}
