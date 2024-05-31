package com.bank.server;

import com.bank.command.Command;
import com.bank.command.CommandFactory;
import com.bank.facade.BankFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * The ClientHandler class handles communication with a single client.
 * It reads commands from the client, processes them using the BankFacade, and sends responses back to the client.
 */
public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private Socket clientSocket;
    private BankFacade bankFacade;

    public ClientHandler(Socket clientSocket, BankFacade bankFacade) {
        this.clientSocket = clientSocket;
        this.bankFacade = bankFacade;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            CommandFactory commandFactory = new CommandFactory(bankFacade);
            Command command;
            while ((command = (Command) ois.readObject()) != null) {
                // Process the command and send the response
                String response = command.execute();
                oos.writeObject(response);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error handling client connection", e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.error("Error closing client socket", e);
            }
        }
    }
}
