package com.springbank.banking_api;

import jakarta.validation.constraints.Positive;

public class AmountRequest {
    @Positive(message = "Amount must be greater than zero")
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
