package burgers.web;

import burgers.domain.Burger;
import burgers.domain.BurgerOrder;
import burgers.domain.Ingredient;
import burgers.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import burgers.repo.BurgerRepository;
import burgers.repo.IngredientRepository;
import burgers.repo.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
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
            @ModelAttribute BurgerOrder burgerOrder,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirAttrs
    ) {
        log.info("saving burger: {}", burger);
        redirAttrs.addFlashAttribute("user",user);
        if (errors.hasErrors()) {
            return "design";
        }
        Burger saved = burgerRepository.save(burger);
        burgerOrder.addBurger(burger);
        burgerOrder.setCcNumber("4041369528912506");
        burgerOrder.setCcExpiration("12/23");
        burgerOrder.setCcCVV("123");
        return "redirect:/orders/current";
    }
}
