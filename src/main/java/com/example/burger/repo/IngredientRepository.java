package com.example.burger.repo;

import com.example.burger.data.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
