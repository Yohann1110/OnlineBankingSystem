package com.bank.state;

import java.io.Serializable;

/**
 * The AccountState interface defines the state-specific behavior for account operations.
 */
public interface AccountState extends Serializable {
    void deposit(double amount);
    void withdraw(double amount);
    void display();
    String getDescription();
}
