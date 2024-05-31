package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The DisplayAccountCommand class implements the Command interface to display account details.
 */
public class DisplayAccountCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DisplayAccountCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to display the account.
     * @param phoneNumber The phone number associated with the account.
     */
    public DisplayAccountCommand(BankFacade bankFacade, String phoneNumber) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Executes the command to display the account details.
     *
     * @return The account details as a string.
     */
    @Override
    public String execute() {
        logger.info("Displaying account with phone number: {}", phoneNumber);
        String result = bankFacade.displayAccount(phoneNumber);
        return result;
    }
}
