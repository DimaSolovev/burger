package com.example.burger.web;

import com.example.burger.data.BurgerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("burgerOrder")
public class OrderController {

    @GetMapping("/current")
    public String showOrder() {
        return "order";
    }

    @PostMapping("/current")
    public String processorder(
            @ModelAttribute BurgerOrder burgerOrder,
            SessionStatus sessionStatus
            ) {
        log.info("Process order :{}",burgerOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
