package com.bank.account;

/**
 * The RewardsAccount class adds new functionalities (e.g., reward points) to the Account objects dynamically.
 */
public class RewardsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private int rewardPoints;

    public RewardsAccount(Account account) {
        super(account.getPhoneNumber(), account.getBalance());
        this.rewardPoints = 0;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        addRewardPoints((int) amount);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
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
