//package com.example.tacocloud.messaging;
//
//import com.example.tacocloud.TacoOrder;
//import com.example.tacocloud.kitchen.messaging.rabbit.KitchenUI;
//import com.example.tacocloud.kitchen.messaging.rabbit.listener.OrderListener;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.kafka.listener.MessageListener;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//    @Autowired
//    private KitchenUI kitchenUI;
//
//    @Bean
//    public ConsumerFactory<String, TacoOrder> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
//
//        // Используем JsonDeserializer для TacoOrder
//        JsonDeserializer<TacoOrder> deserializer = new JsonDeserializer<>(TacoOrder.class);
//        deserializer.addTrustedPackages("*"); // Разрешаем все пакеты для десериализации
//        deserializer.setRemoveTypeHeaders(false); // Оставляем тип заголовков
//
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//
//        return new DefaultKafkaConsumerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer<String, TacoOrder> messageListenerContainer() {
//        // Создаем конфигурацию контейнера для потребителя
//        Map<String, Object> consumerProps = new HashMap<>();
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
//
//        // Используем JsonDeserializer для TacoOrder
//        JsonDeserializer<TacoOrder> deserializer = new JsonDeserializer<>(TacoOrder.class);
//        deserializer.addTrustedPackages("*");
//        deserializer.setRemoveTypeHeaders(false);
//
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//
//        // Конфигурируем контейнер с учетом всех настроек
//        ContainerProperties containerProps = new ContainerProperties("tacoOrders");  // Укажите имя вашего топика
//        containerProps.setMessageListener(new OrderListener(kitchenUI));
//
//        // Создаем контейнер, передавая фабрику потребителей и настройки контейнера
//        return new KafkaMessageListenerContainer<>(new DefaultKafkaConsumerFactory<>(consumerProps), containerProps);
//    }
//}
