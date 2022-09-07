package com.example.concessioninventory.snack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Represents the database layer where snacks are stored
@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    Optional<Snack> findSnackByName(String name);
}
