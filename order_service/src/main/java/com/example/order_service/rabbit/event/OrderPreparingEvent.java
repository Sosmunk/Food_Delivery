package com.example.order_service.rabbit.event;

import lombok.Data;

import java.util.UUID;
/**
 * Событие, сообщающее о том, что заказ готовится
 */
@Data
public class OrderPreparingEvent {
    /**
     * ID заказа
     */
    UUID orderId;
}
