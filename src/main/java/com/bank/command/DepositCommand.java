package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DepositCommand class implements the Command interface to deposit an amount into an account.
 */
public class DepositCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DepositCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;
    private double amount;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to perform the deposit.
     * @param phoneNumber The phone number associated with the account.
     * @param amount The amount to deposit.
     */
    public DepositCommand(BankFacade bankFacade, String phoneNumber, double amount) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    /**
     * Executes the command to deposit the amount into the account.
     *
     * @return A message indicating the result of the deposit.
     */
    @Override
    public String execute() {
        logger.info("Depositing amount: {} to account with phone number: {}", amount, phoneNumber);
        String result = bankFacade.depositToAccount(phoneNumber, amount);
        logger.debug("Deposit result: {}", result);
        return result;
    }
}
