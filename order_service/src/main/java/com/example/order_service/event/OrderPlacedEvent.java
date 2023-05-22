package com.example.order_service.event;

import lombok.Value;

import java.util.UUID;
@Value
public class OrderPlacedEvent {
    UUID orderId;
    UUID accountId;
    Integer orderPrice;
}
