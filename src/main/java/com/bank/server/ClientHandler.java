package com.bank.server;

import com.bank.command.Command;
import com.bank.command.CommandFactory;
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

    public ClientHandler(Socket socket, BankFacade bankFacade) {
        this.clientSocket = socket;
        this.bankFacade = bankFacade;
    }

    @Override
    public void run() {
        logger.info("Thread {} started handling client {}", Thread.currentThread().getName(), clientSocket.getInetAddress());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            CommandFactory commandFactory = new CommandFactory(bankFacade);

            while ((request = in.readLine()) != null) {
                logger.debug("Received request: {}", request);
                Command command = commandFactory.createCommand(request);
                String response = command.execute();
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
}
