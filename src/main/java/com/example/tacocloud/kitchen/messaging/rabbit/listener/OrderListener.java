package com.example.tacocloud.kitchen.messaging.rabbit.listener;

import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.kitchen.messaging.rabbit.KitchenUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }
}
