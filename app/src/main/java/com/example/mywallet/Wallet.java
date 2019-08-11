package com.example.mywallet;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Wallet {
    @Id private long id;
    private int capacity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
