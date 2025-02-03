package com.example.tacocloud.messaging;

import com.example.tacocloud.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//@Service
//public class JmsOrderMessagingService implements OrderMessagingService {
//    private JmsTemplate jms;
//
//    @Autowired
//    public JmsOrderMessagingService(JmsTemplate jms) {
//        this.jms = jms;
//    }
//
////    @Override
////    public void sendOrder(TacoOrder order) {
////        jms.convertAndSend("taco.queue", order);
////    }
//    public void sendOrder(TacoOrder order) {
//        try {
//            // Пробуем отправить сообщение в очередь
//            jms.convertAndSend("taco.queue", "Test Message");
//            System.out.println("Сообщение успешно отправлено!");
//        } catch (Exception e) {
//            // Логируем ошибку, если не удается подключиться
//            System.err.println("Ошибка при подключении к брокеру: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
