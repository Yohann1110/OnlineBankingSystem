package com.bank.server;

import com.bank.facade.BankFacade;
import com.bank.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankServer {
    private static final Logger logger = LoggerFactory.getLogger(BankServer.class);
    private static final int PORT = 12345;
    private List<Transaction> transactions;
    private ExecutorService threadPool;

    public BankServer() {
        transactions = new LinkedList<>();
        threadPool = Executors.newFixedThreadPool(10); // Un seul pool de threads avec 10 threads
    }

    public static void main(String[] args) {
        new BankServer().startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Bank server is running on port {}...", PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Accepted connection from {}", clientSocket.getInetAddress());
                threadPool.execute(new ClientHandler(clientSocket, new BankFacade(transactions)));
            }
        } catch (IOException e) {
            logger.error("Error in server operation", e);
        } finally {
            if (threadPool != null && !threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }
    }
}
