package com.example.order_service.domain.enumerable;

/**
 * Статус заказа
 */
public enum OrderStatus {
    CREATED,
    PAID,
    PREPARING,
    READY,
    IN_DELIVERY,
    DELIVERED
}