package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The CloseAccountCommand class implements the Command interface to close an account.
 */
public class CloseAccountCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(CloseAccountCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to close the account.
     * @param phoneNumber The phone number associated with the account.
     */
    public CloseAccountCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Executes the command to close the account.
     *
     * @return A message indicating the result of the closure.
     */
    @Override
    public String execute() {
        logger.info("Closing account with phone number: {}", phoneNumber);
        String result = bankFacade.closeAccount(phoneNumber);
        logger.debug("Close account result: {}", result);
        return result;
    }
}
