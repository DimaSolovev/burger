package com.example.burger.web;

import com.example.burger.data.Burger;
import com.example.burger.data.BurgerOrder;
import com.example.burger.data.Ingredient;
import com.example.burger.data.User;
import com.example.burger.repo.BurgerRepository;
import com.example.burger.repo.IngredientRepository;
import com.example.burger.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    private final IngredientRepository ingredientRepository;
    private final BurgerRepository burgerRepository;
    private final UserRepository userRepository;

    public BurgerDesignController(IngredientRepository ingredientRepository, BurgerRepository burgerRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.burgerRepository = burgerRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @ModelAttribute(name = "burgerOrder")
    public BurgerOrder burgerOrder() {
        return new BurgerOrder();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

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
    ) {
        log.info("saving burger: {}", burger);
        if (errors.hasErrors()) {
            return "design";
        }
        Burger saved = burgerRepository.save(burger);
        burgerOrder.addBurger(burger);
        return "redirect:/orders/current";
    }
}
