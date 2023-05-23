package com.example.order_service.domain.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 * DTO для отправки на Kitchen микросервис с информацией о всех позициях в заказе
 */
@Value
public class KitchenOrderDTO {

    /**
     * Номер заказа
     */
    UUID orderId;

    /**
     * Информация о позиции блюда в заказе
     */
    @Value
    public static class OrderMenuItem {
        /**
         * Id блюда в заказе {@link com.example.order_service.domain.entity.OrderMenuItem#getOrderMenuItemId() OrderMenuItemId}}
         */
        Long itemId;
        /**
         * Название блюда
         */
        String name;
        /**
         * Количество предметов данной позиции
         */
        Integer quantity;
    }

    /**
     * Список блюд в заказе
     */
    List<OrderMenuItem> orderMenuItems;

}
