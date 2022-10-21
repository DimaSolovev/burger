package burgers.api;

import burgers.domain.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class BurgerView {
    private Long id;
    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();

    public BurgerView(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}