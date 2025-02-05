package com.example.tacocloud.kitchen.messaging.rabbit;

import com.example.tacocloud.TacoOrder;

import org.springframework.stereotype.Component;
@Component
public class RabbitOrderReceiver {
//    private RabbitTemplate rabbit;
//    private MessageConverter converter;
//    @Autowired
//    public RabbitOrderReceiver(RabbitTemplate rabbit) {
//        this.rabbit = rabbit;
//        this.converter = rabbit.getMessageConverter();
//    }
//    public TacoOrder receiveOrder() {
//        Message message = rabbit.receive("tacocloud.order");
//        return message != null
//                ? (TacoOrder) converter.fromMessage(message)
//                : null;
//    }
}
