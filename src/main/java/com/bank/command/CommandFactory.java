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
     * @throws IllegalArgumentException if the command is invalid or arguments are missing.
     */
    public Command createCommand(String input) {
        String[] parts = input.split(" ");
        String commandType = parts[0];

        try {
            switch (commandType) {
                case "CREATE":
                    if (parts.length < 3) {
                        throw new IllegalArgumentException("Invalid CREATE command. Usage: CREATE [phoneNumber] [accountType]");
                    }
                    return new CreateAccountCommand(bankFacade, parts[1], parts[2]);
                case "DEPOSIT":
                    if (parts.length < 3) {
                        throw new IllegalArgumentException("Invalid DEPOSIT command. Usage: DEPOSIT [phoneNumber] [amount]");
                    }
                    return new DepositCommand(bankFacade, parts[1], Double.parseDouble(parts[2]));
                case "WITHDRAW":
                    if (parts.length < 3) {
                        throw new IllegalArgumentException("Invalid WITHDRAW command. Usage: WITHDRAW [phoneNumber] [amount]");
                    }
                    return new WithdrawCommand(bankFacade, parts[1], Double.parseDouble(parts[2]));
                case "DISPLAY":
                    if (parts.length < 2) {
                        throw new IllegalArgumentException("Invalid DISPLAY command. Usage: DISPLAY [phoneNumber]");
                    }
                    return new DisplayAccountCommand(bankFacade, parts[1]);
                case "TRANSFER":
                    if (parts.length < 4) {
                        throw new IllegalArgumentException("Invalid TRANSFER command. Usage: TRANSFER [fromPhoneNumber] [toPhoneNumber] [amount]");
                    }
                    return new TransferCommand(bankFacade, parts[1], parts[2], Double.parseDouble(parts[3]));
                default:
                    throw new IllegalArgumentException("Invalid command: " + commandType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in command: " + input);
        }
    }
}
