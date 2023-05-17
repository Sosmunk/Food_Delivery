package com.example.order_service.repository;

import com.example.order_service.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order getOrderByOrderId(UUID uuid);

}
