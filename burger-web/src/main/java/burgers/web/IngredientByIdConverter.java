package burgers.web;

import burgers.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import burgers.repo.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String source) {
        return ingredientRepository.findById(source).orElse(null);
    }
}
