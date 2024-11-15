package com.example.tacocloud.web;

import com.example.tacocloud.Taco;
import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.User;
import com.example.tacocloud.data.OrderRepository;
import com.example.tacocloud.messaging.OrderMessagingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;
    private OrderProps props;
    private final OrderMessagingService orderMessagingService;

    public OrderController(OrderRepository orderRepo, OrderProps props, OrderMessagingService orderMessagingService) {
        this.orderRepo = orderRepo;
        this.props = props;

        this.orderMessagingService = orderMessagingService;}

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders",orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
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

    @PostMapping("/send")
    public ResponseEntity<?> sendOrder(@RequestBody TacoOrder order) {
        orderMessagingService.sendOrder(order);
        return ResponseEntity.ok("Order sent");
    }

}
