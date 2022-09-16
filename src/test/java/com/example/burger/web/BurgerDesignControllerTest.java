package com.example.burger.web;

import com.example.burger.data.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class BurgerDesignControllerTest {

    @Autowired
    MockMvc mockMvc;

    private List<Ingredient> ingredients;

    @BeforeEach
    public void setup() {
        ingredients = Arrays.asList(
                new Ingredient("WHIT", "Black bun", Ingredient.Type.BUP),
                new Ingredient("BLAC", "White bun", Ingredient.Type.BUP),
                new Ingredient("CHIC", "Chicken", Ingredient.Type.MEAT),
                new Ingredient("PORK", "Pork", Ingredient.Type.MEAT),
                new Ingredient("SALD", "Salad", Ingredient.Type.VEGGIES),
                new Ingredient("CABG", "Cabbage", Ingredient.Type.VEGGIES),
                new Ingredient("RUSS", "Russian", Ingredient.Type.CHEESE),
                new Ingredient("MSDM", "Masdam", Ingredient.Type.CHEESE),
                new Ingredient("KTCP", "Ketchup", Ingredient.Type.SAUCE),
                new Ingredient("MANS", "Mayonnaise", Ingredient.Type.SAUCE)
        );
    }

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attribute("bup", ingredients.subList(0, 2)))
                .andExpect(model().attribute("meat", ingredients.subList(2, 4)))
                .andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
                .andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
                .andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
    }

    @Test
    public void processBurger() throws Exception {
        mockMvc.perform(post("/design")
                        .content("name=Test+Taco&ingredients=WHIT,BLAC,CHIC")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/orders/current"));
    }

    @Test
    public void processBurgerError() throws Exception {
        mockMvc.perform(post("/design")
                        .content("name=Err&ingredients=WHIT,BLAC,CHIC")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attributeHasFieldErrorCode("burger","name","Size"));
    }
}