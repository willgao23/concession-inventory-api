package com.example.concessioninventory.snack;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Represents the database layer where snacks are stored
public interface SnackRepository extends JpaRepository<Snack, Long> {
    Optional<Snack> findSnackByName(String name);
}
