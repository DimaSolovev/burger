package burgers;

import burgers.domain.Burger;
import burgers.domain.Ingredient;
import burgers.domain.User;
import burgers.repo.BurgerRepository;
import burgers.repo.IngredientRepository;
import burgers.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            BurgerRepository burgerRepo,
            IngredientRepository ingredientRepo,
            UserRepository userRepo,
            PasswordEncoder encoder) {
        return args -> {

            Ingredient whiteBun = new Ingredient("WHIT", "White bun", Ingredient.Type.BUN);
            Ingredient blackBun = new Ingredient("BLAC", "Black bun", Ingredient.Type.BUN);
            Ingredient chicken = new Ingredient("CHIC", "Chicken", Ingredient.Type.MEAT);
            Ingredient pork = new Ingredient("PORK", "Pork", Ingredient.Type.MEAT);
            Ingredient salad = new Ingredient("SALD", "Salad", Ingredient.Type.VEGGIES);
            Ingredient cabbage = new Ingredient("CABG", "Cabbage", Ingredient.Type.VEGGIES);
            Ingredient russian = new Ingredient("RUSS", "Russian", Ingredient.Type.CHEESE);
            Ingredient masdam = new Ingredient("MSDM", "Masdam", Ingredient.Type.CHEESE);
            Ingredient ketchup = new Ingredient("KTCP", "Ketchup", Ingredient.Type.SAUCE);
            Ingredient mayonnaise = new Ingredient("MANS", "Mayonnaise", Ingredient.Type.SAUCE);

            ingredientRepo.save(whiteBun);
            ingredientRepo.save(blackBun);
            ingredientRepo.save(chicken);
            ingredientRepo.save(pork);
            ingredientRepo.save(salad);
            ingredientRepo.save(cabbage);
            ingredientRepo.save(russian);
            ingredientRepo.save(masdam);
            ingredientRepo.save(ketchup);
            ingredientRepo.save(mayonnaise);

            userRepo.save(new User("admin", encoder.encode("1"),
                    "Dmitrii", "Sanitarnii", "Habarovsk", "Hub",
                    "76227", "123-123-1234"));

            Burger burger1 = new Burger();
            burger1.setName("Carnivore");
            burger1.setIngredients(Arrays.asList(whiteBun, blackBun, chicken));
            burgerRepo.save(burger1);

            Burger burger2 = new Burger();
            burger2.setName("Bovine Bounty");
            burger2.setIngredients(Arrays.asList(whiteBun, pork, ketchup, masdam));
            burgerRepo.save(burger2);

            Burger burger3 = new Burger();
            burger3.setName("Veg-Out");
            burger3.setIngredients(Arrays.asList(blackBun, pork, chicken, russian, mayonnaise));
            burgerRepo.save(burger3);
        };
    }

}
