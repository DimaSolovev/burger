package burgers;

import burgers.domain.Burger;
import burgers.domain.Ingredient;
import burgers.domain.User;
import burgers.repo2.BurgerRepository;
import burgers.repo2.IngredientRepository;
import burgers.repo2.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            BurgerRepository burgerRepo,
            IngredientRepository ingredientRepo,
            UserRepository userRepo,
            PasswordEncoder encoder) {

        return new CommandLineRunner() {

            @Override
            public void run(String... args) throws Exception {
                Ingredient whiteBun = saveAnIngredient("WHIT", "White bun", Ingredient.Type.BUN);
                Ingredient blackBun = saveAnIngredient("BLAC", "Black bun", Ingredient.Type.BUN);
                Ingredient chicken = saveAnIngredient("CHIC", "Chicken", Ingredient.Type.MEAT);
                Ingredient pork = saveAnIngredient("PORK", "Pork", Ingredient.Type.MEAT);
                Ingredient salad = saveAnIngredient("SALD", "Salad", Ingredient.Type.VEGGIES);
                Ingredient cabbage = saveAnIngredient("CABG", "Cabbage", Ingredient.Type.VEGGIES);
                Ingredient russian = saveAnIngredient("RUSS", "Russian", Ingredient.Type.CHEESE);
                Ingredient masdam = saveAnIngredient("MSDM", "Masdam", Ingredient.Type.CHEESE);
                Ingredient ketchup = saveAnIngredient("KTCP", "Ketchup", Ingredient.Type.SAUCE);
                Ingredient mayonnaise = saveAnIngredient("MANS", "Mayonnaise", Ingredient.Type.SAUCE);

                Burger burger1 = new Burger();
                burger1.setName("Carnivore");
                burger1.addIngredient(whiteBun);
                burger1.addIngredient(blackBun);
                burger1.addIngredient(chicken);
                burger1.addIngredient(pork);
                burger1.addIngredient(salad);
                burgerRepo.save(burger1).subscribe();

                Burger burger2 = new Burger();
                burger2.setName("Bovine Bounty");
                burger2.addIngredient(whiteBun);
                burger2.addIngredient(blackBun);
                burger2.addIngredient(cabbage);
                burger2.addIngredient(russian);
                burger2.addIngredient(masdam);
                burgerRepo.save(burger2).subscribe();

                Burger burger3 = new Burger();
                burger3.setName("Veg-Out");
                burger3.addIngredient(whiteBun);
                burger3.addIngredient(blackBun);
                burger3.addIngredient(cabbage);
                burger3.addIngredient(mayonnaise);
                burger3.addIngredient(ketchup);
                burgerRepo.save(burger3).subscribe();


                userRepo.save(new User("admin", encoder.encode("1"),
                        "Dmitrii", "Sanitarnii", "Habarovsk", "Hub",
                        "76227", "123-123-1234"));

            }

            private Ingredient saveAnIngredient(String id, String name, Ingredient.Type type) {
                Ingredient ingredient = new Ingredient(id, name, type);
                ingredientRepo.save(ingredient).subscribe();
                return ingredient;
            }
        };
    }

}
