package com.skblab.project.account_service.amqp.handler;

import com.skblab.project.account_service.dto.amqp.OrderPaidEvent;
import com.skblab.project.account_service.service.LoyaltyCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * <b>Обработчик оплаченных заказов из RabbitMQ</b>
 */
@Service
@RequiredArgsConstructor
public class OrderPaidHandler {
    private final LoyaltyCardService loyaltyCardService;

    @RabbitListener(queues = "paid_order_queue")
    public void handleOrderPaid(OrderPaidEvent event) {
        loyaltyCardService.accumulateBonuses(event);
    }
}
