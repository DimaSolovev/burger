package burgers.restclient;

import burgers.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

    public static void main(String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(BurgerCloudClient burgerCloudClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY IDE");
            log.info("Ingredient:  " + burgerCloudClient.getIngredientById("WHIT"));
            log.info("GETTING ALL INGREDIENTS");
            List<Ingredient> ingredients = burgerCloudClient.getAllIngredients();
            log.info("All ingredients:");
            for (Ingredient ingredient : ingredients) {
                log.info("   - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner putAnIngredient(BurgerCloudClient burgerCloudClient) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            Ingredient before = burgerCloudClient.getIngredientById("WHIT");
            log.info("BEFORE:  " + before);
            burgerCloudClient.updateIngredient(new Ingredient("WHIT", "Updated bun", Ingredient.Type.BUN));
            Ingredient after = burgerCloudClient.getIngredientById("WHIT");
            log.info("AFTER:  " + after);
        };
    }
}
