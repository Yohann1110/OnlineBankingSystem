package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ActivateAccountCommand class implements the Command interface to activate an account.
 */
public class ActivateAccountCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(ActivateAccountCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to activate the account.
     * @param phoneNumber The phone number associated with the account.
     */
    public ActivateAccountCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Executes the command to activate the account.
     *
     * @return A message indicating the result of the activation.
     */
    @Override
    public String execute() {
        logger.info("Activating account with phone number: {}", phoneNumber);
        String result = bankFacade.activateAccount(phoneNumber);
        logger.debug("Activate account result: {}", result);
        return result;
    }
}
