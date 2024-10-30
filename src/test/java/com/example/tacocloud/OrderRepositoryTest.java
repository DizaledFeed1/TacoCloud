package com.example.tacocloud;

import com.example.tacocloud.data.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepo;

    @Test
    public void testSaveOrder() {
        TacoOrder order = new TacoOrder();
        order.setDeliveryName("Test Name");
        order.setCcNumber("1234567812345678");
        order.setCcExpiration("12/24");
        order.setCcCVV("123");

        TacoOrder savedOrder = orderRepo.save(order);
        assertNotNull(savedOrder.getId());
    }
}

