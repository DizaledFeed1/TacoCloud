package com.example.tacocloud.web;

import com.example.tacocloud.Taco;
import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.User;
import com.example.tacocloud.data.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        for (Taco taco : order.getTacos()) {
            taco.setTacoOrder(order);
            orderRepo.save(order);
        }
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
