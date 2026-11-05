package com.springbank.banking_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accountNumber;
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    protected Transaction() {

    }
    public Transaction(String accountNumber, String type, double amount, LocalDateTime timestamp) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    public long getId() {
        return id;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getAmount() {
        return amount;
    }
    public String getType() {
        return type;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
