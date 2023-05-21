package com.example.order_service.service;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.enumerable.OrderStatus;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest, Claims claims);

    void changeOrderStatus(UUID uuid, OrderStatus orderStatus);

    void processReadyOrder(UUID orderId);

    List<OrderResponse> getAccountOrders(UUID accountId);
}
