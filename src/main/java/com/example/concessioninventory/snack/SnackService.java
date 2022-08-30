package com.example.concessioninventory.snack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Represents the business logic layer of the concession stand API
@Service
public class SnackService {

    private SnackRepository snackRepository;

    //EFFECTS: constructs a snack service with the given snack repository
    @Autowired
    public void SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    //EFFECTS: returns a list of all snacks inside the repository
    public List<Snack> getSnacks() {
        return snackRepository.findAll();
    }
}
