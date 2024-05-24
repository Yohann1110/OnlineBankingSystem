package com.bank.command;

import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The CreateAccountCommand class implements the Command interface to create a new bank account.
 */
public class CreateAccountCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(CreateAccountCommand.class);
    private BankFacade bankFacade;
    private String phoneNumber;
    private String type;

    /**
     * Constructor to initialize the command with the necessary parameters.
     *
     * @param bankFacade The facade used to create the account.
     * @param phoneNumber The phone number associated with the account to be created.
     * @param type The type of the account (e.g., PREMIUM, REWARDS).
     */
    public CreateAccountCommand(BankFacade bankFacade, String phoneNumber, String type) {
        this.bankFacade = bankFacade;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    /**
     * Executes the command to create a new account.
     *
     * @return A message indicating the result of the account creation.
     */
    @Override
    public String execute() {
        logger.info("Creating account with phone number: {} and type: {}", phoneNumber, type);
        String result = bankFacade.createAccount(phoneNumber, type);
        logger.debug("Create account result: {}", result);
        return result;
    }
}
