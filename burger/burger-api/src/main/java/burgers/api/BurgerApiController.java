package burgers.api;

import burgers.domain.Burger;
import burgers.repo.BurgerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/burgers", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class BurgerApiController {

    private BurgerRepository burgerRepository;

    public BurgerApiController(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @GetMapping(params = "recent")
    public Flux<Burger> recentBurgers() {
        return Flux.fromIterable(burgerRepository.findAll()).take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Burger postBurger(@RequestBody Burger burger) {
        return burgerRepository.save(burger);
    }

    @GetMapping("/{id}")
    public Burger burgerById(@PathVariable("id") Long id) {
        return burgerRepository.findById(id).orElse(null);
    }
}
