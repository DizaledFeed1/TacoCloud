package com.example.tacocloud.kitchen.messaging.rabbit.listener;

import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.kitchen.messaging.rabbit.KitchenUI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

//    @RabbitListener(queues = "tacocloud.order")
//    @Async
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }
}
