package com.skblab.project.kitchen_service.event;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@Value
public class OrderPaidEvent {
    UUID orderId;

    List<OrderMenuItem> orderMenuItems;

    @Value
    public static class OrderMenuItem {
        Long itemId;
        String name;
        int quantity;
    }
}
