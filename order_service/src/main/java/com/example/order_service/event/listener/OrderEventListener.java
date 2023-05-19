package com.example.order_service.event.listener;

import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.event.publisher.OrderMessagePublisher;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderService orderService;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderMessagePublisher orderMessagePublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void mockOrderPayment(OrderPlacedEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.PAID);
        Order order = orderRepository.getOrderByOrderId(event.getOrderId());
        orderMessagePublisher.sendPaidOrderToKitchen(orderFactory.createKitchenOrderDtoFrom(order));
    }
}