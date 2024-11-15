package com.example.tacocloud.messaging;

import com.example.tacocloud.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
