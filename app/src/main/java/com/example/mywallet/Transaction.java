package com.example.mywallet;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Transaction {
    @Id private long id;
    private int amount;
    private int amountType;
    private String type;
    private String description;

    public Transaction(int amount, int amountType, String type, String description) {
        this.amount = amount;
        this.amountType = amountType;
        this.type = type;
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountType() {
        return amountType;
    }
}
