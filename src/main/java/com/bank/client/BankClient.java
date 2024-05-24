package com.bank.client;

import com.bank.command.Command;
import com.bank.command.CommandFactory;
import com.bank.facade.BankFacade;
import com.bank.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * The BankClient class connects to the bank server and sends commands to perform various banking operations.
 */
public class BankClient {
    private static final Logger logger = LoggerFactory.getLogger(BankClient.class);
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private List<Transaction> transactions;
    private BankFacade bankFacade;
    private CommandFactory commandFactory;

    /**
     * Constructor to initialize the BankClient.
     */
    public BankClient() {
        // Initialize the transactions list and the facade for bank operations
        transactions = new LinkedList<>();
        bankFacade = new BankFacade(transactions);
        commandFactory = new CommandFactory(bankFacade);
    }

    public static void main(String[] args) {
        // Start the client application
        new BankClient().startClient();
    }

    /**
     * Establishes a connection to the server, sends commands, and processes responses.
     */
    public void startClient() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            logger.info("Connected to server at {}:{}", SERVER_ADDRESS, SERVER_PORT);

            // Array of commands to be executed for testing the system
            String[] commands = {
                    "CREATE account1 PREMIUM",
                    "CREATE account2 REWARDS",
                    "DEPOSIT account1 500",
                    "DEPOSIT account2 300",
                    "WITHDRAW account1 200",
                    "WITHDRAW account2 100",
                    "DISPLAY account1",
                    "DISPLAY account2",
                    "SUSPEND account1",
                    "DEPOSIT account1 100",  // Should not be allowed
                    "WITHDRAW account1 50",  // Should not be allowed
                    "DISPLAY account1",
                    "ACTIVATE account1",
                    "DEPOSIT account1 100",
                    "WITHDRAW account1 50",
                    "DISPLAY account1",
                    "CLOSE account2",
                    "DEPOSIT account2 100",  // Should not be allowed
                    "WITHDRAW account2 50",  // Should not be allowed
                    "DISPLAY account2"
            };

            // Loop through each command, send it to the server, and process the response
            for (String commandString : commands) {
                Command command = commandFactory.createCommand(commandString);
                String response = command.execute();
                logger.debug("Server response: {}", response);
                System.out.println("Server response: " + response);
                Thread.sleep(1000); // Wait for a second between commands for better readability
            }

        } catch (IOException | InterruptedException e) {
            logger.error("Error in client operation", e);
        }
    }
}
