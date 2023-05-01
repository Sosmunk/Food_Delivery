package com.skblab.project.kitchen_service.handler;

import com.skblab.project.kitchen_service.event.OrderPaidEvent;
import com.skblab.project.kitchen_service.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaidHandler {
    private final KitchenService kitchenService;

    @RabbitListener(queues = "paid_order_queue")
    public void handleOrderPaid(OrderPaidEvent event) {
        kitchenService.changeOrderStatusToIsPreparing(event.getOrderId());
    }
}
