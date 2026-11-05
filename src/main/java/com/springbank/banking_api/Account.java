package com.springbank.banking_api;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    private String accountNumber;
    private String ownerName;
    protected double balance;

    protected Account() {

    }

    public Account(String accountNumber, String ownerName, double balance) {
        if (accountNumber == null || accountNumber.isEmpty() || accountNumber.charAt(0) == '-') {
            throw new IllegalArgumentException("Invalid Account Number");
        }
        if (ownerName == null || ownerName.isEmpty() || ownerName.length() > 100) {
            throw new IllegalArgumentException("Invalid Owner Name");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Opening Balance must be greater than or equal to zero");
        }
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

    public double deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("deposit Amount Must be greater than Zero");
        }
        else {
            balance += amount;
        }
        return balance;
    }

    public synchronized double withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw Amount Must be grater than zero");
        }
        else if (amount > balance) {
            throw new InsufficientFundsException("Insufficient Funds; "+"Requested amount: "+amount+" is greater than balance amount: "+balance+" in your Account");
        }
        else {
            balance -= amount;
            System.out.println("Withdraw of Amount: "+amount+" in your Account is sucess; remaining balance: "+balance);
        }
        return balance;
    }
}
