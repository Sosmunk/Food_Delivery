package com.example.order_service.service;

import com.example.order_service.domain.dto.request.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
