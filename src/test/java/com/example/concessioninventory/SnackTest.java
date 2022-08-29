package com.example.concessioninventory;

import com.example.concessioninventory.snack.Snack;
import com.example.concessioninventory.snack.SnackCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnackTest {
    private Snack testSnack;

    @BeforeEach
    public void setUp() {
        testSnack = new Snack("Peanut M&Ms", 1.99, SnackCategory.Sweet, List.of("peanuts"), 15);
    }

    @Test
    public void testConstructor() {
        assertEquals("Peanut M&Ms", testSnack.getName());
        assertEquals(1.99, testSnack.getPrice());
        assertEquals(SnackCategory.Sweet, testSnack.getCategory());
        assertEquals(List.of("peanuts"), testSnack.getAllergens());
        assertEquals(15, testSnack.getStock());
    }

    @Test
    public void testSetNameOnce() {
        testSnack.setName("Smarties");

        assertEquals("Smarties", testSnack.getName());
    }

    @Test
    public void testSetNameMultipleTimes() {
        testSnack.setName("Rockets");
        testSnack.setName("Mars Bar");

        assertEquals("Mars Bar", testSnack.getName());
    }

    @Test
    public void testSetPriceOnce() {
        testSnack.setPrice(0.99);

        assertEquals(0.99, testSnack.getPrice());
    }

    @Test
    public void testSetPriceMultipleTimes() {
        testSnack.setPrice(1.5);
        testSnack.setPrice(2.5);

        assertEquals(2.5, testSnack.getPrice());
    }

    @Test
    public void testSetCategoryOnce() {
        testSnack.setCategory(SnackCategory.Drink);

        assertEquals(SnackCategory.Drink, testSnack.getCategory());
    }

    @Test
    public void testSetCategoryMultipleTimes() {
        testSnack.setCategory(SnackCategory.Drink);
        testSnack.setCategory(SnackCategory.Salty);

        assertEquals(SnackCategory.Salty, testSnack.getCategory());
    }

    @Test
    public void testSetAllergensOnce() {
        testSnack.setAllergens(List.of("peanuts, shellfish"));

        assertEquals(List.of("peanuts, shellfish"), testSnack.getAllergens());
    }

    @Test
    public void testSetAllergensMultipleTimes() {
        testSnack.setAllergens(List.of("peanuts, strawberries"));
        testSnack.setAllergens(List.of());

        assertEquals(List.of(), testSnack.getAllergens());
    }

    @Test
    public void testSetStockOnce() {
        testSnack.setStock(20);

        assertEquals(20, testSnack.getStock());
    }

    @Test
    public void testSetStockMultipleTimes() {
        testSnack.setStock(12);
        testSnack.setStock(24);

        assertEquals(24, testSnack.getStock());
    }
}
