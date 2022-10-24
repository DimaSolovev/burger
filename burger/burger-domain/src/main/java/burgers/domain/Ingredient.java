package burgers.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Ingredient {
    @Id
    private Long id;

    private @NonNull String slug;

    private @NotNull String name;
    private @NotNull Type type;

    public Ingredient(@NonNull String slug, String name, Type type) {
        this.slug = slug;
        this.name = name;
        this.type = type;
    }

    public enum Type {
        BUN, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
