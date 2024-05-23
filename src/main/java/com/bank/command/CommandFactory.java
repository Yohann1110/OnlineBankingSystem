package com.bank.command;

import com.bank.facade.BankFacade;

public class CommandFactory {
    private static BankFacade bankFacade;

    public CommandFactory(BankFacade bankFacade) {
        CommandFactory.bankFacade = bankFacade;
    }

    public static Command createCommand(String input) {
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
            case "SUSPEND":
                return new SuspendAccountCommand(bankFacade, parts[1]);
            case "ACTIVATE":
                return new ActivateAccountCommand(bankFacade, parts[1]);
            case "CLOSE":
                return new CloseAccountCommand(bankFacade, parts[1]);
            default:
                throw new IllegalArgumentException("Invalid command: " + commandType);
        }
    }
}
