package com.example.order_service.rabbit.event;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderPreparingEvent {
    UUID orderId;
}
