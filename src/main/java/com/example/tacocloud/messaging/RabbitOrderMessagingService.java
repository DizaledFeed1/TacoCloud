package com.example.tacocloud.messaging;

import com.example.tacocloud.TacoOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService
        implements OrderMessagingService {
    private static final Logger log = LoggerFactory.getLogger(RabbitOrderMessagingService.class);
    private RabbitTemplate rabbit;
    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    public void sendOrder(TacoOrder order) {
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties props = new MessageProperties();
        props.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, props);

        System.out.println("Отправка заказа в RabbitMQ: {}");
        rabbit.send("tacocloud.exchange", "tacocloud.order", message);
        log.info("Сообщение отправлено успешно");
    }
}
