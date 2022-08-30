package com.example.concessioninventory.snack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //EFFECTS: if there is a snack in the database with the given name, if the
    // newPrice is not zero, edit the snack's price to the new price; if the newStock is not null
    // edit the snack's stock to the new Stock; else throw an IllegalStateException
    @Transactional
    public void editSnack(Long snackId, Double newPrice, Integer newStock) {
        Snack snack = snackRepository.findById(snackId).get();

        if (snack.equals(null)) {
            throw new IllegalStateException("There is no snack with ID " + snackId + " in your concession stand!");
        }

        if (newPrice != null && newPrice != 0) {
            snack.setPrice(newPrice);
        }

        if (newStock != null) {
            snack.setStock(newStock);
        }
    }
}
