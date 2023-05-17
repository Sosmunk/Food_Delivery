package com.example.order_service.domain.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class KitchenOrderDTO {

    UUID uuid;

    @Value
    public static class OrderMenuItem {
        Long id;

        String name;

        Integer quantity;
    }

    List<OrderMenuItem> orderMenuItems;
}
