package com.example.concessioninventory.snack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //EFFECTS: if no snack in database has the same name as given snack, add given snack to database;
    //else, throw IllegalStateException
    public void addNewSnack(Snack snack) {
        Optional<Snack> snackOptional = snackRepository.findSnackByName(snack.getName());

        if (snackOptional.isPresent()) {
            throw new IllegalStateException("Snack with name " + snack.getName() +
                    " is already in the concession stand!");
        }

        snackRepository.save(snack);
    }

    //EFFECTS: if there is a snack in the database with the given name, delete it;
    //else, throw IllegalStateException
    public void deleteSnack(String snackName) {
        Optional<Snack> snackOptional = snackRepository.findSnackByName(snackName);

        if (snackOptional.isEmpty()) {
            throw new IllegalStateException("There is no snack named " + snackName + " in your concession stand!");
        }

        snackRepository.delete(snackOptional.get());
    }
}
