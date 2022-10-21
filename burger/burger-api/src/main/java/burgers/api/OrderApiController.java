package burgers.api;

import burgers.domain.BurgerOrder;


import burgers.messaging.OrderMessagingService;
import burgers.repo.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class OrderApiController {

    private OrderRepository repo;
    private OrderMessagingService messageService;

    private BurgerOrderAggregateService aggregateService;

    public OrderApiController(OrderRepository repo, OrderMessagingService messageService, BurgerOrderAggregateService aggregateService) {
        this.repo = repo;
        this.messageService = messageService;
        this.aggregateService = aggregateService;
    }

    @GetMapping
    Flux<BurgerOrder> allOrders() {
        return repo.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BurgerOrder> postOrder(@RequestBody BurgerOrder burgerOrder) {
        messageService.sendOrder(burgerOrder);
        return aggregateService.save(burgerOrder);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Mono<BurgerOrder> putOrder(@RequestBody Mono<BurgerOrder> burgerOrder) {
        return burgerOrder.flatMap(repo::save);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Mono<BurgerOrder> patchOrder(@PathVariable("orderId") Long orderId,
                                        @RequestBody BurgerOrder burgerOrder) {
        return repo.findById(orderId)
                .map(order -> {
                    if (burgerOrder.getDeliveryName() != null) {
                        order.setDeliveryName(burgerOrder.getDeliveryName());
                    }
                    if (burgerOrder.getDeliveryStreet() != null) {
                        order.setDeliveryStreet(burgerOrder.getDeliveryStreet());
                    }
                    if (burgerOrder.getDeliveryCity() != null) {
                        order.setDeliveryCity(burgerOrder.getDeliveryCity());
                    }
                    if (burgerOrder.getDeliveryState() != null) {
                        order.setDeliveryState(burgerOrder.getDeliveryState());
                    }
                    if (burgerOrder.getDeliveryZip() != null) {
                        order.setDeliveryZip(burgerOrder.getDeliveryState());
                    }
                    if (burgerOrder.getCcNumber() != null) {
                        order.setCcNumber(burgerOrder.getCcNumber());
                    }
                    if (burgerOrder.getCcExpiration() != null) {
                        order.setCcExpiration(burgerOrder.getCcExpiration());
                    }
                    if (burgerOrder.getCcCVV() != null) {
                        order.setCcCVV(burgerOrder.getCcCVV());
                    }
                    return order;
                })
                .flatMap(repo::save);

    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}

