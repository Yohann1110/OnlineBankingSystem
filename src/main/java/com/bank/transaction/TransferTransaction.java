package com.bank.transaction;

/**
 * The TransferTransaction class represents a transfer transaction.
 * It extends the Transaction class to add specific behavior for transfer transactions.
 */
public class TransferTransaction extends Transaction {
    public TransferTransaction(String transactionId, String fromAccount, String toAccount, double amount, String date) {
        super(transactionId, fromAccount, toAccount, amount, date);
    }

    // Add specific methods for transfer transactions if needed
}
