package com.bank.command;

import com.bank.facade.BankFacade;

/**
 * The CommandFactory class is responsible for creating command objects based on input strings.
 * It simplifies the client code by encapsulating the logic of which command to instantiate.
 */
public class CommandFactory {
    private BankFacade bankFacade;

    /**
     * Constructor to initialize the CommandFactory with a reference to the BankFacade.
     *
     * @param bankFacade The facade used to execute commands.
     */
    public CommandFactory(BankFacade bankFacade) {
        this.bankFacade = bankFacade;
    }

    /**
     * Creates a command object based on the input string.
     *
     * @param input The input string representing the command.
     * @return The created command object.
     */
    public Command createCommand(String input) {
        String[] parts = input.split(" ");
        String commandType = parts[0];

        switch (commandType) {
            case "CREATE":
                return new CreateAccountCommand(bankFacade, parts[1], parts[2]);
            case "DEPOSIT":
                return new DepositCommand(bankFacade, parts[1], Double.parseDouble(parts[2]));
            case "WITHDRAW":
                return new WithdrawCommand(bankFacade, parts[1], Double.parseDouble(parts[2]));
            case "DISPLAY":
                return new DisplayAccountCommand(bankFacade, parts[1]);
            case "TRANSFER":
                return new TransferCommand(bankFacade, parts[1], parts[2], Double.parseDouble(parts[3]));
            default:
                throw new IllegalArgumentException("Invalid command: " + commandType);
        }
    }
}
