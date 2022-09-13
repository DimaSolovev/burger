package com.example.burger.web;

import com.example.burger.data.Burger;
import com.example.burger.data.BurgerOrder;
import com.example.burger.data.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.burger.data.Ingredient.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class BurgerDesignController {

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @ModelAttribute(name = "burgerOrder")
    public BurgerOrder burgerOrder() {
        return new BurgerOrder();
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("WHIT", "Black bun", Type.BUP),
                new Ingredient("BLAC", "White bun", Type.BUP),
                new Ingredient("CHIC", "Chicken", Type.MEAT),
                new Ingredient("PORK", "Pork", Type.MEAT),
                new Ingredient("SALD", "Salad", Type.VEGGIES),
                new Ingredient("CABG", "Cabbage", Type.VEGGIES),
                new Ingredient("RUSS", "Russian", Type.CHEESE),
                new Ingredient("MSDM", "Masdam", Type.CHEESE),
                new Ingredient("KTCP", "Ketchup", Type.SAUCE),
                new Ingredient("MANS", "Mayonnaise", Type.SAUCE)
        );

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String designBurger() {
        return "design";
    }

    @PostMapping
    public String processBurger(
            @ModelAttribute @Valid Burger burger,
            Errors errors,
            @ModelAttribute BurgerOrder burgerOrder
    ){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Process burger: {}",burger);
        burgerOrder.addBurger(burger);
        return "redirect:/order/current";
    }
}
