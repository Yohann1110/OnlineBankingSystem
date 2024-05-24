package com.bank.state;

/**
 * The AccountState interface defines the interface for state-specific behavior.
 * It is implemented by concrete states such as ActiveState, SuspendedState, and ClosedState.
 */
public interface AccountState {
    void deposit(double amount);
    void withdraw(double amount);
    void display();
}
