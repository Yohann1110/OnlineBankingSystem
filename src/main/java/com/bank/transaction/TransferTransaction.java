package com.bank.transaction;

public class TransferTransaction extends Transaction {
    public TransferTransaction(String transactionId, String fromAccount, String toAccount, double amount, String date) {
        super(transactionId, fromAccount, toAccount, amount, date);
    }

    // Add specific methods for transfer transactions if needed
}
