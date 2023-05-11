package com.example.order_service.service.impl;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = orderFactory.createOrderFrom(orderRequest);
        orderRepository.save(order);
        return String.format("Заказ принят. Его идентификатор: %s", order.getOrderId().toString());

    }

}
