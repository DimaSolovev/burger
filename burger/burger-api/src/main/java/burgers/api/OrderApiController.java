package burgers.api;

import burgers.domain.BurgerOrder;
import burgers.messaging.OrderMessagingService;
import burgers.repo.OrderRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class OrderApiController {

    private OrderRepository orderRepository;

    private OrderMessagingService messageService;

    public OrderApiController(OrderRepository orderRepository, OrderMessagingService messageService) {
        this.orderRepository = orderRepository;
        this.messageService = messageService;
    }

    @GetMapping
    Iterable<BurgerOrder> allOrders() {
        return orderRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BurgerOrder postOrder(@RequestBody BurgerOrder burgerOrder) {
        messageService.sendOrder(burgerOrder);
        return orderRepository.save(burgerOrder);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public BurgerOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody BurgerOrder burgerOrder) {
        burgerOrder.setId(orderId);
        return orderRepository.save(burgerOrder);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public BurgerOrder patchOrder(@PathVariable("orderId") Long orderId,
                                  @RequestBody BurgerOrder patch) {
        BurgerOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}

