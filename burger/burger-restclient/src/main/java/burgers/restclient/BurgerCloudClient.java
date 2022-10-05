package burgers.restclient;

import burgers.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BurgerCloudClient {

    private RestTemplate rest;

    public BurgerCloudClient(RestTemplate rest) {
        this.rest = rest;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public List<Ingredient> getAllIngredients() {
//        return rest.exchange("http://localhost:8080/data-api/ingredients",
//                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {})
//                .getBody();
        Ingredient[] forNow = rest.getForObject("http://localhost:8080/data-api/ingredients", Ingredient[].class);
        return Arrays.asList(forNow);
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/data-api/ingredients/{id}",
                ingredient, ingredient.getId());
    }
}
