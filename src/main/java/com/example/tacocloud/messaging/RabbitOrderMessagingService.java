package com.example.tacocloud.messaging;

import com.example.tacocloud.TacoOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService
        implements OrderMessagingService {
    private RabbitTemplate rabbit;
    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    public void sendOrder(TacoOrder order) {
        rabbit.convertAndSend("tacocloud.order", order);
    }
}
