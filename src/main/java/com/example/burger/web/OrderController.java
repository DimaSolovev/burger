package com.example.burger.web;

import com.example.burger.data.BurgerOrder;
import com.example.burger.data.User;
import com.example.burger.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirAttrs
    ) {
        redirAttrs.addAttribute("user",user);
        if (errors.hasErrors()) {
            return "order";
        }
        log.info("Process order :{}", burgerOrder);
        burgerOrder.setUser(user);
        orderRepository.save(burgerOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
