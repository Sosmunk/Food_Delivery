package com.example.order_service.event;

import lombok.Value;

import java.util.UUID;

/**
 * Событие, в котором сообщается о том, что заказ был добавлен в обработку
 */
@Value
public class OrderPlacedEvent {
    UUID orderId;
    UUID accountId;
    Integer orderPrice;
}
