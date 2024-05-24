package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The WithdrawCommand class implements the Command interface to withdraw an amount from an account.
 */
public class WithdrawCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(WithdrawCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;
    private double amount;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to perform the withdrawal.
     * @param phoneNumber The phone number associated with the account.
     * @param amount The amount to withdraw.
     */
    public WithdrawCommand(BankFacade bankFacade, String phoneNumber, double amount) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    /**
     * Executes the command to withdraw the amount from the account.
     *
     * @return A message indicating the result of the withdrawal.
     */
    @Override
    public String execute() {
        logger.info("Withdrawing amount: {} from account with phone number: {}", amount, phoneNumber);
        String result = bankFacade.withdrawFromAccount(phoneNumber, amount);
        logger.debug("Withdraw result: {}", result);
        return result;
    }
}
