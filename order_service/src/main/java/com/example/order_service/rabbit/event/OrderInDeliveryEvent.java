package com.example.order_service.rabbit.event;

import lombok.Data;

import java.util.UUID;
/**
 * Событие, сообщающее о том, что заказ был передан в доставку
 */
@Data
public class OrderInDeliveryEvent {
    /**
     * ID заказа
     */
    UUID orderId;
}
