package com.example.tacocloud.kitchen.messaging.rabbit.listener;

import ch.qos.logback.classic.Logger;
import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.kitchen.messaging.rabbit.KitchenUI;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private KitchenUI ui;
    private Logger log;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics="tacocloud.orders.topic", groupId = "my-consumer-group")
    public void handle(
            TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        log.info("Received from partition {} with timestamp {}",
                record.partition(), record.timestamp());
        ui.displayOrder(order);
    }
}
