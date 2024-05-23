package com.bank.server;

import com.bank.account.Account;
import com.bank.account.PremiumAccount;
import com.bank.account.RewardsAccount;
import com.bank.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private Socket clientSocket;
    private List<Transaction> transactions;
    private Map<String, Account> accounts;

    public ClientHandler(Socket socket, List<Transaction> transactions) {
        this.clientSocket = socket;
        this.transactions = transactions;
        this.accounts = new HashMap<>();
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
                return createAccount(parts);
            case "DEPOSIT":
                return depositToAccount(parts);
            case "WITHDRAW":
                return withdrawFromAccount(parts);
            case "DISPLAY":
                return displayAccount(parts);
            default:
                return "Invalid command.";
        }
    }

    private String createAccount(String[] parts) {
        if (parts.length < 3) {
            return "Invalid CREATE command. Usage: CREATE <accountId> <type>";
        }
        String accountId = parts[1];
        String type = parts[2];

        Account account;
        if (type.equals("PREMIUM")) {
            account = new PremiumAccount(new Account(accountId, 0));
        } else if (type.equals("REWARDS")) {
            account = new RewardsAccount(new Account(accountId, 0));
        } else {
            return "Unknown account type.";
        }

        accounts.put(accountId, account);
        logger.info("Created account: {} of type {}", accountId, type);
        return "Account created: " + accountId + " of type " + type;
    }

    private String depositToAccount(String[] parts) {
        if (parts.length < 3) {
            return "Invalid DEPOSIT command. Usage: DEPOSIT <accountId> <amount>";
        }
        String accountId = parts[1];
        double amount = Double.parseDouble(parts[2]);

        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }

        account.deposit(amount);
        logger.info("Deposited {} to account {}", amount, accountId);
        return "Deposited " + amount + " to account " + accountId;
    }

    private String withdrawFromAccount(String[] parts) {
        if (parts.length < 3) {
            return "Invalid WITHDRAW command. Usage: WITHDRAW <accountId> <amount>";
        }
        String accountId = parts[1];
        double amount = Double.parseDouble(parts[2]);

        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }

        account.withdraw(amount);
        logger.info("Withdrew {} from account {}", amount, accountId);
        return "Withdrew " + amount + " from account " + accountId;
    }

    private String displayAccount(String[] parts) {
        if (parts.length < 2) {
            return "Invalid DISPLAY command. Usage: DISPLAY <accountId>";
        }
        String accountId = parts[1];

        Account account = accounts.get(accountId);
        if (account == null) {
            return "Account not found: " + accountId;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        account.display();

        System.out.flush();
        System.setOut(old);

        String result = baos.toString();
        logger.info("Displaying account: {}\n{}", accountId, result);
        return result;
    }
}
