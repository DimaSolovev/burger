package com.example.burger.web;

import com.example.burger.data.Ingredient;
import com.example.burger.data.Ingredient.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> map = new HashMap<>();

    public IngredientByIdConverter() {
        map.put("WHIT", new Ingredient("WHIT", "Black bun", Type.BUP));
        map.put("BLAC", new Ingredient("BLAC", "White bun", Type.BUP));
        map.put("CHIC", new Ingredient("CHIC", "Chicken", Type.MEAT));
        map.put("PORK", new Ingredient("PORK", "Pork", Type.MEAT));
        map.put("SALD", new Ingredient("SALD", "Salad", Type.VEGGIES));
        map.put("CABG", new Ingredient("CABG", "Cabbage", Type.VEGGIES));
        map.put("RUSS", new Ingredient("RUSS", "Russian", Type.CHEESE));
        map.put("MSDM", new Ingredient("MSDM", "Masdam", Type.CHEESE));
        map.put("KTCP", new Ingredient("KTCP", "Ketchup", Type.SAUCE));
        map.put("MANS", new Ingredient("MANS", "Mayonnaise", Type.SAUCE));
    }

    @Override
    public Ingredient convert(String source) {
        return map.get(source);
    }
}
