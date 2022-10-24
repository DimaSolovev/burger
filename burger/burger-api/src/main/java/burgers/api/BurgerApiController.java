package burgers.api;

import burgers.domain.Burger;
import burgers.domain.Ingredient;
import burgers.repo2.BurgerRepository;
import burgers.repo2.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/burgers", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class BurgerApiController {

    private BurgerRepository burgerRepo;

    private IngredientRepository ingredientRepo;

    public BurgerApiController(BurgerRepository burgerRepository, IngredientRepository ingredientRepository) {
        this.burgerRepo = burgerRepository;
        this.ingredientRepo = ingredientRepository;
    }

    @GetMapping(params="recent")
    public Flux<BurgerView> recentTacos() {
        return burgerRepo
                .findAll()
                .take(12)
                .map(taco -> {
                    BurgerView burgerView = new BurgerView(taco.getId(), taco.getName());
                    taco.getIngredientIds()
                            .forEach(ingredientId -> {
                                ingredientRepo.findById(ingredientId)
                                        .subscribe(ingredient -> {
                                            burgerView.addIngredient(ingredient);
                                        });

                            });
                    return burgerView;
                });
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Burger> postBurger(@RequestBody BurgerView burgerView) {
        Burger burger = new Burger(burgerView.getName());
        for (Ingredient ingredient : burgerView.getIngredients()) {
            burger.addIngredient(ingredient);
        }
        return burgerRepo.save(burger);
    }

    @GetMapping("/{id}")
    public Mono<BurgerView> burgerById(@PathVariable("id") Long id) {
        return burgerRepo.findById(id)
                .map(burger -> {
                    BurgerView burgerView = new BurgerView(burger.getId(), burger.getName());
                    burger.getIngredientIds()
                            .forEach(ingredientId -> {
                                ingredientRepo.findById(ingredientId)
                                        .subscribe(burgerView::addIngredient);
                            });
                    return burgerView;
                });
    }
}
