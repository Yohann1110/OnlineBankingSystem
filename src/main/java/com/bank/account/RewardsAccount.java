package com.bank.account;

/**
 * The RewardsAccount class is a concrete decorator that extends AccountDecorator.
 * It adds new functionalities (e.g., reward points) to the Account objects dynamically.
 */
public class RewardsAccount extends AccountDecorator {
    private static final long serialVersionUID = 1L;
    private int rewardPoints;

    public RewardsAccount(Account decoratedAccount) {
        super(decoratedAccount);
        this.rewardPoints = 0;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount); // Call the decorated account's deposit method
        addRewardPoints((int) amount); // Add reward points based on the deposited amount
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount); // Call the decorated account's withdraw method
        System.out.println("Rewards account withdrawal: " + amount);
    }

    private void addRewardPoints(int points) {
        rewardPoints += points;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Reward Points: " + rewardPoints);
        System.out.println("This is a rewards account.");
    }
}
