package com.example.order_service.service.impl;

import com.example.order_service.common.annotation.LogMethodExecution;
import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    @LogMethodExecution
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderFactory.createOrderFrom(orderRequest);
        orderRepository.save(order);
        log.info("Service: Заказ {} сохранен", order.getOrderId());
        return orderFactory.createOrderResponseFrom(order);

    }

}
