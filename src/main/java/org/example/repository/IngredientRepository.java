package org.example.repository;

import org.example.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save (Ingredient ingredient);
}
