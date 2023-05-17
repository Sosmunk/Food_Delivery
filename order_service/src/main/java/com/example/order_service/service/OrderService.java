package com.example.order_service.service;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.enumerable.OrderStatus;

import java.util.UUID;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

    void changeOrderStatus(UUID uuid, OrderStatus orderStatus);
}
