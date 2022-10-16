package burgers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Ingredient {

    private final String name;
    private final Type type;

    public enum Type {
        BUN, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
