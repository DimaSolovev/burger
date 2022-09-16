package com.example.burger.repo;

import com.example.burger.data.Ingredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    public void findById(){
        Optional<Ingredient> whit = ingredientRepository.findById("WHIT");
        assertThat(whit.isPresent()).isTrue();
        assertThat(whit.get()).isEqualTo(new Ingredient("WHIT", "White bun", Ingredient.Type.BUN));

        Optional<Ingredient> xxxx = ingredientRepository.findById("xxxx");
        assertThat(xxxx.isEmpty()).isTrue();

    }

}