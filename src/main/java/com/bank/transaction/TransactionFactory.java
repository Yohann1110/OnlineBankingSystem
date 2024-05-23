package com.bank.transaction;

public class TransactionFactory {
    public static Transaction createTransaction(String type, String transactionId, String fromAccount, String toAccount, double amount, String date) {
        if (type.equals("Transfer")) {
            return new TransferTransaction(transactionId, fromAccount, toAccount, amount, date);
        }
        // Add other types of transactions
        return null;
    }
}
