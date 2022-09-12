package com.example.burger.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Burger {
    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();
}
