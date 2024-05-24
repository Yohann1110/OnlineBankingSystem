package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The TransferCommand class implements the Command interface to transfer money between accounts.
 */
public class TransferCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(TransferCommand.class);
    private BankFacade bankFacade;
    private String fromPhoneNumber;
    private String toPhoneNumber;
    private double amount;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to perform the transfer.
     * @param fromPhoneNumber The phone number associated with the account to transfer money from.
     * @param toPhoneNumber The phone number associated with the account to transfer money to.
     * @param amount The amount to transfer.
     */
    public TransferCommand(BankFacade bankFacade, String fromPhoneNumber, String toPhoneNumber, double amount) {
        this.bankFacade = bankFacade;
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amount = amount;
    }

    /**
     * Executes the command to transfer the amount from one account to another.
     *
     * @return A message indicating the result of the transfer.
     */
    @Override
    public String execute() {
        logger.info("Transferring amount: {} from account with phone number: {} to account with phone number: {}", amount, fromPhoneNumber, toPhoneNumber);
        String result = bankFacade.transfer(fromPhoneNumber, toPhoneNumber, amount);
        logger.debug("Transfer result: {}", result);
        return result;
    }
}
