package com.springbank.banking_api;

public class AccountResponse {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public AccountResponse(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public double getBalance() {
        return balance;
    }
}
