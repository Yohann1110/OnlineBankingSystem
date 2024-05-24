package com.bank.command;

/**
 * The Command interface defines a method for executing a command.
 * Concrete command classes will implement this interface to perform specific actions.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @return The result of the command execution.
     */
    String execute();
}
