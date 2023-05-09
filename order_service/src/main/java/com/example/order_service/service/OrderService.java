package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderFactory orderFactory;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = orderFactory.createOrderFrom(orderRequest);
        return "ok not saved" + order.getOrderMenuItems();
    }

}