package com.example.tacocloud.web.api;

import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.messaging.OrderMessagingService;
import com.example.tacocloud.data.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080") // Разрешаем кросс-доменные запросы с фронтенда
public class OrderApiController {

    private final OrderRepository repo;
    private final OrderMessagingService messageService;

    // Конструктор для внедрения зависимостей
    public OrderApiController(OrderRepository repo, OrderMessagingService messageService) {
        this.repo = repo;
        this.messageService = messageService;
    }

    // Метод для обработки POST-запроса на создание заказа
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED) // Статус ответа 201 - Created
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        // Отправляем заказ через очередь сообщений
        messageService.sendOrder(order);
        // Сохраняем заказ в репозитории
        return repo.save(order);
    }
}

