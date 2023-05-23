package com.example.order_service.rabbit.event;

import lombok.Data;

import java.util.UUID;

/**
 * Событие, сообщающее о том, что заказ был доставлен
 */
@Data
public class OrderDeliveredEvent {
    /**
     * ID заказа
     */
    UUID orderId;
}
