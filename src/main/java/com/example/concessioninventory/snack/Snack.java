package com.example.concessioninventory.snack;

import javax.persistence.*;

// Represents a snack item in the concession inventory
@Entity
@Table
public class Snack {
    @Id
    @SequenceGenerator(
            name = "snack_sequence",
            sequenceName = "snack_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "snack_sequence"
    )

    private String name;
    private double price;
    private SnackCategory category;
    private int stock;

    public Snack() {
    }

    //EFFECTS: creates a snack with the given name, price, category, allergens, and stock
    public Snack(String name, double price, SnackCategory category, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SnackCategory getCategory() {
        return category;
    }

    public void setCategory(SnackCategory category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
