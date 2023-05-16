package com.example.order_service.event.listener;

import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.event.OrderPlacedEvent;
import com.example.order_service.event.OrderStatusChangedEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderService orderService;
    private final ApplicationEventPublisher eventPublisher;
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    // Костыль да еще и с Async
    public void mockOrderPayment(OrderPlacedEvent event) throws InterruptedException {
        Thread.sleep(10000);
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.PAID);
        eventPublisher.publishEvent(OrderStatusChangedEvent.from(event.getOrderId(), OrderStatus.PAID));
    }


}
