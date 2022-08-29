package com.example.concessioninventory.snack;

import java.util.List;

// Represents a snack item in the concession inventory
public class Snack {

    private String name;
    private double price;
    private SnackCategory category;
    private List<String> allergens;
    private int stock;

    //EFFECTS: creates a snack with the given name, price, category, allergens, and stock
    public Snack(String name, double price, SnackCategory category, List<String> allergens, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.allergens = allergens;
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

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
