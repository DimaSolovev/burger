package com.example.burger;

import com.example.burger.data.Ingredient;
import com.example.burger.repo.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BurgerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(IngredientRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("WHIT", "White bun", Ingredient.Type.BUN));
                repo.save(new Ingredient("BLAC", "Black bun", Ingredient.Type.BUN));
                repo.save(new Ingredient("CHIC", "Chicken", Ingredient.Type.MEAT));
                repo.save(new Ingredient("PORK", "Pork", Ingredient.Type.MEAT));
                repo.save(new Ingredient("SALD", "Salad", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("CABG", "Cabbage", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("RUSS", "Russian", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("MSDM", "Masdam", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("KTCP", "Ketchup", Ingredient.Type.SAUCE));
                repo.save(new Ingredient("MANS", "Mayonnaise", Ingredient.Type.SAUCE));
            }
        };
    }
}
