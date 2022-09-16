package com.example.burger.web;

import com.example.burger.data.BurgerOrder;
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

    @GetMapping("/current")
    public String showOrder() {
        return "order";
    }

    @PostMapping("/current")
    public String processOrder(
            @ModelAttribute @Valid BurgerOrder burgerOrder,
            Errors errors,
            SessionStatus sessionStatus
    ) {
        if (errors.hasErrors()) {
            return "order";
        }
        log.info("Process order :{}", burgerOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
