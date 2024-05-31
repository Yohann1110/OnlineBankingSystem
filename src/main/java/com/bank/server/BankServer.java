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

/**
 * The BankServer class initializes and starts the bank server, accepting client connections
 * and handling them using a thread pool.
 */
public class BankServer {
    private static final Logger logger = LoggerFactory.getLogger(BankServer.class);
    private static final int PORT = 12345;
    private List<Transaction> transactions;
    private ExecutorService threadPool;

    public BankServer() {
        // Initialize the transactions list and the thread pool
        transactions = new LinkedList<>();
        threadPool = Executors.newFixedThreadPool(10); // Single thread pool with 10 threads
    }

    public static void main(String[] args) {
        // Start the server
        new BankServer().startServer();
    }

    /**
     * Starts the bank server, accepts client connections, and delegates handling to the thread pool.
     */
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Bank server is running on port {}...", PORT);

            BankFacade bankFacade = new BankFacade(transactions);
            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                logger.info("Accepted connection from {}", clientSocket.getInetAddress());
                // Delegate handling to the thread pool
                threadPool.execute(new ClientHandler(clientSocket, bankFacade));
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
