package com.example.order_service.domain.dto.response;

import com.example.order_service.domain.entity.Address;
import com.example.order_service.domain.enumerable.OrderStatus;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class OrderResponse {
    UUID orderId;

    @Value
    public static class OrderMenuItemInfo {

        @Value
        public static class MenuItemInfo {
            String name;

            Integer price;
        }
        MenuItemInfo menuItem;

        Integer quantity;
    }
    List<OrderMenuItemInfo> orderMenuItems;

    OrderStatus orderStatus;

    Address address;
}
