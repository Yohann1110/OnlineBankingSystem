package com.bank.transaction;

public class TransferTransaction extends Transaction {
    // Spécificités de la transaction de transfert

    public TransferTransaction(String transactionId, String fromAccount, String toAccount, double amount, String date) {
        super(transactionId, fromAccount, toAccount, amount, date);
    }

    // Méthodes spécifiques aux transactions de transfert
}
