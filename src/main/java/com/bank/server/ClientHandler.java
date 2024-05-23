package com.bank.server;

import com.bank.facade.BankFacade;
import com.bank.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private Socket clientSocket;
    private BankFacade bankFacade;

    public ClientHandler(Socket socket, List<Transaction> transactions) {
        this.clientSocket = socket;
        this.bankFacade = new BankFacade(transactions);
    }

    @Override
    public void run() {
        logger.info("Thread {} started handling client {}", Thread.currentThread().getName(), clientSocket.getInetAddress());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                logger.debug("Received request: {}", request);
                String response = handleRequest(request);
                out.println(response);
                logger.debug("Sent response: {}", response);
            }
        } catch (IOException e) {
            logger.error("Error handling client connection", e);
        } finally {
            try {
                clientSocket.close();
                logger.info("Client socket closed for client {}", clientSocket.getInetAddress());
            } catch (IOException e) {
                logger.error("Error closing client socket", e);
            }
        }
        logger.info("Thread {} finished handling client {}", Thread.currentThread().getName(), clientSocket.getInetAddress());
    }

    private String handleRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];
        logger.info("Handling command: {}", command);

        switch (command) {
            case "CREATE":
                return bankFacade.createAccount(parts[1], parts[2]);
            case "DEPOSIT":
                return bankFacade.depositToAccount(parts[1], Double.parseDouble(parts[2]));
            case "WITHDRAW":
                return bankFacade.withdrawFromAccount(parts[1], Double.parseDouble(parts[2]));
            case "DISPLAY":
                return bankFacade.displayAccount(parts[1]);
            case "SUSPEND":
                return bankFacade.suspendAccount(parts[1]);
            case "CLOSE":
                return bankFacade.closeAccount(parts[1]);
            case "ACTIVATE":
                return bankFacade.activateAccount(parts[1]);
            default:
                return "Invalid command.";
        }
    }
}
