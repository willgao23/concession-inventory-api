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

    //EFFECTS: deletes a given snack from the database
    @DeleteMapping (path = "{snackId}")
    public void deleteSnack(@PathVariable("snackId") Long snackId) {
        snackService.deleteSnack(snackId);
    }

    //EFFECTS: edits a given snack's price or stock
    @PutMapping (path = "{snackId}")
    public void editSnack(@PathVariable("snackId") Long snackId,
                          @RequestParam(required = false) Double newPrice,
                          @RequestParam(required = false) Integer newStock) {
        snackService.editSnack(snackId, newPrice, newStock);
    }
}
