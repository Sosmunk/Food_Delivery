package com.example.order_service.event.listener;

import com.example.order_service.domain.dto.AccountOrderDTO;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.event.OrderStatusChangedEvent;
import com.example.order_service.rabbit.publisher.OrderMessagePublisher;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.factory.OrderFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Обработчик событий, прослушивающий изменения в статусе заказа
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderService orderService;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final OrderMessagePublisher orderMessagePublisher;

    /**
     * Mock метод оплаты заказа
     * @param event {@link OrderPlacedEvent}
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void mockOrderPayment(OrderPlacedEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.PAID);
        Order order = orderRepository.getOrderByOrderId(event.getOrderId());
        orderMessagePublisher.sendPaidOrderToAccountService(new AccountOrderDTO(event.getAccountId(), event.getOrderPrice(), 0));
        orderMessagePublisher.sendPaidOrderToKitchen(orderFactory.createKitchenOrderDtoFrom(order));
    }

    /**
     * Метод, который логирует все изменения о статусе заказа
     * @param event {@link OrderStatusChangedEvent}
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void logOrderStatusChanged(OrderStatusChangedEvent event) {
        log.info("Статус заказа {} изменен на {}", event.getOrderId(), event.getOrderStatus());
    }
}
