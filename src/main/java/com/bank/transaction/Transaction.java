package com.bank.transaction;

import java.io.Serializable;

/**
 * The Transaction class represents a financial transaction.
 * It stores details such as transaction type, phone numbers involved, amount, and date.
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transactionId;
    private String fromPhoneNumber;
    private String toPhoneNumber;
    private double amount;
    private String date;

    /**
     * Constructor for transactions that involve two phone numbers (e.g., transfer).
     *
     * @param transactionId   The unique identifier for the transaction.
     * @param fromPhoneNumber The phone number associated with the source account.
     * @param toPhoneNumber   The phone number associated with the target account.
     * @param amount          The amount involved in the transaction.
     * @param date            The date of the transaction.
     */
    public Transaction(String transactionId, String fromPhoneNumber, String toPhoneNumber, double amount, String date) {
        this.transactionId = transactionId;
        this.fromPhoneNumber = fromPhoneNumber;
        this.toPhoneNumber = toPhoneNumber;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Constructor for transactions that involve a single phone number (e.g., deposit, withdraw).
     *
     * @param transactionId   The unique identifier for the transaction.
     * @param phoneNumber     The phone number associated with the account.
     * @param amount          The amount involved in the transaction.
     * @param date            The date of the transaction.
     */
    public Transaction(String transactionId, String phoneNumber, double amount, String date) {
        this(transactionId, phoneNumber, phoneNumber, amount, date);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", fromPhoneNumber='" + fromPhoneNumber + '\'' +
                ", toPhoneNumber='" + toPhoneNumber + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
