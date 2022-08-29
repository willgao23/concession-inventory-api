package com.example.concessioninventory;

import com.example.concessioninventory.snack.Snack;
import com.example.concessioninventory.snack.SnackCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {
    private Snack testSnack;

    @BeforeEach
    public void setUp() {
        testSnack = new Snack("Peanut M&Ms", 1.99, SnackCategory.Sweet, true,
                false, false, 15);
    }

    @Test
    public void testConstructor() {
        assertEquals("Peanut M&Ms", testSnack.getName());
        assertEquals(1.99, testSnack.getPrice());
        assertEquals(SnackCategory.Sweet, testSnack.getCategory());
        assertTrue(testSnack.isHasNuts());
        assertFalse(testSnack.isHasGluten());
        assertFalse(testSnack.isHasLactose());
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

    @Test
    public void testSetHasNutsOnce() {
        testSnack.setHasNuts(false);

        assertFalse(testSnack.isHasNuts());
    }

    @Test
    public void testSetHasNutsMultipleTimes() {
        testSnack.setHasNuts(false);
        testSnack.setHasNuts(true);

        assertTrue(testSnack.isHasNuts());
    }

    @Test
    public void testSetHasGlutenOnce() {
        testSnack.setHasGluten(true);

        assertTrue(testSnack.isHasGluten());
    }

    @Test
    public void testSetHasGlutenMultipleTimes() {
        testSnack.setHasGluten(true);
        testSnack.setHasGluten(false);

        assertFalse(testSnack.isHasGluten());
    }

    @Test
    public void testSetHasLactoseOnce() {
        testSnack.setHasLactose(true);

        assertTrue(testSnack.isHasLactose());
    }

    @Test
    public void testSetHasLactoseMultipleTimes() {
        testSnack.setHasLactose(true);
        testSnack.setHasLactose(false);

        assertFalse(testSnack.isHasLactose());
    }
}
