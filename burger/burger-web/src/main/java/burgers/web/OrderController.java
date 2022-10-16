package burgers.web;


import burgers.domain.BurgerOrder;
import burgers.domain.User;
import burgers.messaging.OrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import burgers.repo.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("burgerOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;

    private OrderMessagingService messageService;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps, OrderMessagingService messageService) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
        this.messageService = messageService;
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
        messageService.sendOrder(burgerOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String getOrders(
            @AuthenticationPrincipal User user,
            Model model
    ){
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());//нулевая страница размером 10
        model.addAttribute("orders",orderRepository.findByUserOrderByPlacedAtDesc(user,pageable));
        return "orderList";
    }
}
