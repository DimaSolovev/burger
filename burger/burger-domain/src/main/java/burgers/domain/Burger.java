package burgers.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Burger {

    @Id
    private Long id;

    private @NotNull String name;

    private Set<Long> ingredientIds = new HashSet<>();

    public Burger(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientIds.add(ingredient.getId());
    }
}
