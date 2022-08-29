package com.example.concessioninventory.snack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (path = "concession-inventory-api/v1")
public class SnackController {

    private SnackService snackService;

    @Autowired
    public void SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public List<Snack> getSnacks() {
        return snackService.getSnacks();
    }
}
