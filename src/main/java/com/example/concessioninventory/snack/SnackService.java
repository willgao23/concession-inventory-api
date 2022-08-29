package com.example.concessioninventory.snack;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackService {

    public List<Snack> getSnacks() {
        return List.of(new Snack("Peanut M&Ms", 1.99, SnackCategory.Sweet, 15));
    }
}
