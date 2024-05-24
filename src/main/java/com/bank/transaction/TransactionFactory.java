package com.bank.transaction;

/**
 * The TransactionFactory class is responsible for creating different types of transactions.
 * It uses the factory method pattern to encapsulate the creation logic.
 */
public class TransactionFactory {
    public static Transaction createTransaction(String type, String transactionId, String fromAccount, String toAccount, double amount, String date) {
        if (type.equals("Transfer")) {
            return new TransferTransaction(transactionId, fromAccount, toAccount, amount, date);
        }
        // Add other types of transactions as needed
        return null;
    }
}
