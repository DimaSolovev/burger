package com.example.burger.data;

import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        BUP, MEAT, VEGGIES, CHEESE, SAUCE
    }
}
