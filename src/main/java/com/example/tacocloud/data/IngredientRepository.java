package com.example.tacocloud.data;

import com.example.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
}