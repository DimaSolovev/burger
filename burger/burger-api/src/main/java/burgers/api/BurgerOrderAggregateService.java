package burgers.api;

import burgers.domain.Burger;
import burgers.domain.BurgerOrder;
import burgers.repo2.OrderRepository;
import burgers.repo2.BurgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BurgerOrderAggregateService {

    private final BurgerRepository burgerRepo;
    private final OrderRepository orderRepo;

    public Mono<BurgerOrder> save(BurgerOrder burgerOrder) {
        return Mono.just(burgerOrder).flatMap(order -> {
            List<Burger> burgers = order.getBurgers();
            order.setBurgers(new ArrayList<>());
            return burgerRepo.saveAll(burgers).map(burger -> {
                burgerOrder.addBurger(burger);
                return burgerOrder;
            }).last();
        }).flatMap(orderRepo::save);
    }

    public Mono<BurgerOrder> findById(Long id) {
        return orderRepo
                .findById(id)
                .flatMap(order -> {
                    return burgerRepo.findAllById(order.getBurgerIds()) // <1>
                            .map(taco -> {
                                order.addBurger(taco);
                                return order;
                            }).last();
                });
    }
}
