package com.example.order_service.event;

import com.example.order_service.domain.entity.Order;
import lombok.Value;

import java.util.UUID;
@Value
public class OrderPlacedEvent {
    UUID orderId;

    public static OrderPlacedEvent from(Order order) {
        return new OrderPlacedEvent(order.getOrderId());
    }
}
