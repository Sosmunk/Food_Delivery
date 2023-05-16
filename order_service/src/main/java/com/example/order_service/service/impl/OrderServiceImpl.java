package com.example.order_service.service.impl;

import com.example.order_service.common.annotation.LogMethodExecution;
import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.event.OrderStatusChangedEvent;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;
    @LogMethodExecution
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderFactory.createOrderFrom(orderRequest);
        orderRepository.save(order);
        log.info("Service: Заказ {} сохранен", order.getOrderId());
        eventPublisher.publishEvent(OrderPlacedEvent.from(order));
        return orderFactory.createOrderResponseFrom(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeOrderStatus(UUID uuid, OrderStatus orderStatus) {
        Order order = orderRepository.getOrderByOrderId(uuid);
        order.setOrderStatus(orderStatus);
        eventPublisher.publishEvent(OrderStatusChangedEvent.from(order));
        orderRepository.save(order);
    }

}
