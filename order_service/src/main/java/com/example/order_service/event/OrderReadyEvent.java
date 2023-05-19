package com.example.order_service.event;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderReadyEvent {
    UUID orderId;
}
