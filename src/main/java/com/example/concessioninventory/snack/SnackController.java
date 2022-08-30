package com.example.concessioninventory.snack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Represents the API layer of the concession stand API
@RestController
@RequestMapping (path = "concession-inventory-api/v1")
public class SnackController {

    private SnackService snackService;

    //EFFECTS: constructs a snack controller with the given snack service
    @Autowired
    public void SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    //EFFECTS: returns a list of snacks from the database
    @GetMapping
    public List<Snack> getSnacks() {
        return snackService.getSnacks();
    }

    //EFFECTS: adds a given snack into the database
    @PostMapping
    public void addNewSnack(@RequestBody Snack snack) {
        snackService.addNewSnack(snack);
    }
}
