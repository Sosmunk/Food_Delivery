package com.example.order_service.domain.dto.response;

import com.example.order_service.domain.entity.Address;
import com.example.order_service.domain.enumerable.OrderStatus;
import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 * Response для OrderController, содержащий информацию о заказе, необходимую для пользователя
 */
@Value
public class OrderResponse {
    /**
     * ID заказа
     */
    UUID orderId;

    /**
     * Информация о позиции в заказе
     */
    @Value
    public static class OrderMenuItemInfo {

        /**
         * Конкретное блюдо в меню
         */
        @Value
        public static class MenuItemInfo {
            /**
             * Название блюда в меню
             */
            String name;

            /**
             * Цена продукта
             */
            Integer price;
        }
        MenuItemInfo menuItem;

        /**
         * Количество продукта
         */
        Integer quantity;
    }

    /**
     * Список продуктов в заказе
     */
    List<OrderMenuItemInfo> orderMenuItems;

    /**
     * Статус заказа
     */
    OrderStatus orderStatus;

    /**
     * Адрес заказа
     */
    Address address;
}
