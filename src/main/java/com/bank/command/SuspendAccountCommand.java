package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SuspendAccountCommand class implements the Command interface to suspend an account.
 */
public class SuspendAccountCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(SuspendAccountCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to suspend the account.
     * @param phoneNumber The phone number associated with the account.
     */
    public SuspendAccountCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Executes the command to suspend the account.
     *
     * @return A message indicating the result of the suspension.
     */
    @Override
    public String execute() {
        logger.info("Suspending account with phone number: {}", phoneNumber);
        String result = bankFacade.suspendAccount(phoneNumber);
        logger.debug("Suspend account result: {}", result);
        return result;
    }
}
