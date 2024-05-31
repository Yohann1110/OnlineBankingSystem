package com.bank.transaction;

import java.io.Serializable;

/**
 * The Transaction class represents a financial transaction.
 * It stores details such as transaction ID, accounts involved, amount, and date.
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transactionId;
    private String fromAccount;
    private String toAccount;
    private double amount;
    private String date;

    public Transaction(String transactionId, String fromAccount, String toAccount, double amount, String date) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
