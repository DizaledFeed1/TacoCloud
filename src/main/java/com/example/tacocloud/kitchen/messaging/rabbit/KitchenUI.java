package com.example.tacocloud.kitchen.messaging.rabbit;

import com.example.tacocloud.TacoOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KitchenUI {
    int a;
    String b;
    public void displayOrder(TacoOrder order) {
        System.out.println(order);
    }
}
