package com.example.burger.web;

import com.example.burger.data.Ingredient;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String,Ingredient> map = new HashMap<>();


    @Override
    public Ingredient convert(String source) {
        return null;
    }
}
