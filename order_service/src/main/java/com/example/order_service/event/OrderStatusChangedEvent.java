package com.example.order_service.event;

import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.enumerable.OrderStatus;
import lombok.Value;

import java.util.UUID;

/**
 * Событие, в котором сообщается о том, что статус заказа был изменен
 */
@Value
public class OrderStatusChangedEvent {

    UUID orderId;

    OrderStatus orderStatus;

    public static OrderStatusChangedEvent from(Order order) {
        return new OrderStatusChangedEvent(order.getOrderId(), order.getOrderStatus());
    }
}
