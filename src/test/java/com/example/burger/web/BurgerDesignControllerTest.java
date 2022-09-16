package com.example.burger.web;

import com.example.burger.data.Ingredient;
import com.example.burger.repo.IngredientRepository;
import com.example.burger.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class BurgerDesignControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private OrderRepository orderRepository;


    private List<Ingredient> ingredients;

//    @BeforeEach
//    public void setup() {
//        ingredients = Arrays.asList(
//                new Ingredient("WHIT", "Black bun", Ingredient.Type.BUN),
//                new Ingredient("BLAC", "White bun", Ingredient.Type.BUN),
//                new Ingredient("CHIC", "Chicken", Ingredient.Type.MEAT),
//                new Ingredient("PORK", "Pork", Ingredient.Type.MEAT),
//                new Ingredient("SALD", "Salad", Ingredient.Type.VEGGIES),
//                new Ingredient("CABG", "Cabbage", Ingredient.Type.VEGGIES),
//                new Ingredient("RUSS", "Russian", Ingredient.Type.CHEESE),
//                new Ingredient("MSDM", "Masdam", Ingredient.Type.CHEESE),
//                new Ingredient("KTCP", "Ketchup", Ingredient.Type.SAUCE),
//                new Ingredient("MANS", "Mayonnaise", Ingredient.Type.SAUCE)
//        );
//    }

    @Test
    public void testShowDesignForm() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"));
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
                .andExpect(model().attributeHasFieldErrorCode("burger", "name", "Size"));
    }
}