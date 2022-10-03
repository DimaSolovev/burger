package burgers;

import burgers.domain.Ingredient;
import burgers.domain.User;
import burgers.repo.IngredientRepository;
import burgers.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

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

                userRepo.save(new User("1", encoder.encode("1"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234"));
            }
        };
    }

}
