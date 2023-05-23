package com.example.order_service.rabbit.event;

import lombok.Data;

import java.util.UUID;
/**
 * Событие, сообщающее о том, что заказ был приготовлен
 */
@Data
public class OrderReadyEvent {
    /**
     * ID заказа
     */
    UUID orderId;
}
