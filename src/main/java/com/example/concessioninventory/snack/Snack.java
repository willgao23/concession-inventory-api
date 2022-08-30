package com.example.concessioninventory.snack;

import javax.persistence.*;

// Represents a snack item in the concession inventory
@Entity
@Table
public class Snack {
    @SequenceGenerator(
            name = "snack_sequence",
            sequenceName = "snack_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "snack_sequence"
    )
    @Id
    private Long id;
    private String name;
    private Double price;
    private SnackCategory category;
    private boolean hasNuts;
    private boolean hasGluten;
    private boolean hasLactose;
    private Integer stock;

    public Snack() {
    }

    //EFFECTS: creates a snack with the given name, price, category, allergens, and stock
    public Snack(String name, double price, SnackCategory category, boolean hasNuts,
                 boolean hasGluten, boolean hasLactose, int stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.hasNuts = hasNuts;
        this.hasGluten = hasGluten;
        this.hasLactose = hasLactose;
        this.stock = stock;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SnackCategory getCategory() {
        return category;
    }

    public void setCategory(SnackCategory category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isHasNuts() {
        return hasNuts;
    }

    public void setHasNuts(boolean hasNuts) {
        this.hasNuts = hasNuts;
    }

    public boolean isHasGluten() {
        return hasGluten;
    }

    public void setHasGluten(boolean hasGluten) {
        this.hasGluten = hasGluten;
    }

    public boolean isHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }
}
