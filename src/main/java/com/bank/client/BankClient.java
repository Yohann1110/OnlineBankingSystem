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

public class BankClient {
    private static final Logger logger = LoggerFactory.getLogger(BankClient.class);
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private List<Transaction> transactions;
    private BankFacade bankFacade;
    private CommandFactory commandFactory;

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
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))) {

            logger.info("Connected to server at {}:{}", SERVER_ADDRESS, SERVER_PORT);

            String userInput;
            while (true) {
                System.out.print("Enter command: ");
                userInput = userIn.readLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    Command command = commandFactory.createCommand(userInput);
                    String response = command.execute();
                    System.out.println(response);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid command or error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            logger.error("Error in client operation", e);
        }
    }
}
