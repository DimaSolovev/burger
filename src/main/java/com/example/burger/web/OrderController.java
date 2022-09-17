package com.example.burger.web;

import com.example.burger.data.BurgerOrder;
import com.example.burger.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String showOrder() {
        return "order";
    }

    @PostMapping()
    public String processOrder(
            @Valid BurgerOrder burgerOrder,
            Errors errors,
            SessionStatus sessionStatus
    ) {
        if (errors.hasErrors()) {
            return "order";
        }
        log.info("Process order :{}", burgerOrder);
        orderRepository.save(burgerOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
