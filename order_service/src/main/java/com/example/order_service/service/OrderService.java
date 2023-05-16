package com.example.order_service.service;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
