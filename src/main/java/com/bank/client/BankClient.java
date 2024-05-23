package com.bank.client;

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

    public BankClient() {
        transactions = new LinkedList<>();
    }

    public static void main(String[] args) {
        new BankClient().startClient();
    }

    public void startClient() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            logger.info("Connected to server at {}:{}", SERVER_ADDRESS, SERVER_PORT);

            // Automate some commands for testing
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

            for (String command : commands) {
                logger.debug("Sending command: {}", command);
                out.println(command);
                String response = in.readLine();
                logger.debug("Server response: {}", response);
                System.out.println("Server response: " + response);
                Thread.sleep(1000); // Wait for a second between commands for better readability
            }

        } catch (IOException | InterruptedException e) {
            logger.error("Error in client operation", e);
        }
    }
}
