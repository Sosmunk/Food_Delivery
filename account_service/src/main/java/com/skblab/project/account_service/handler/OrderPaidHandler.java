package com.skblab.project.account_service.handler;

import com.skblab.project.account_service.event.OrderPaidEvent;
import com.skblab.project.account_service.service.LoyaltyCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaidHandler {
    private final LoyaltyCardService loyaltyCardService;

    @RabbitListener(queues = "paid_order_queue")
    public void handleOrderPaid(OrderPaidEvent event) {
        loyaltyCardService.accumulateBonuses(event);
    }
}
