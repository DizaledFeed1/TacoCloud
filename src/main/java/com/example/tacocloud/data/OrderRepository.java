package com.example.tacocloud.data;

import com.example.tacocloud.TacoOrder;
import com.example.tacocloud.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {
//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//
//    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
//            String deliveryZip, Date startDate, Date endDate);
List<TacoOrder> findByUserOrderByPlacedAtDesc(
        User user, Pageable pageable);
}
