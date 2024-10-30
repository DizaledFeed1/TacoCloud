package com.example.tacocloud.data;

import com.example.tacocloud.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//
//    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
//            String deliveryZip, Date startDate, Date endDate);
}
