package com.skblab.project.kitchen_service.service;

import com.skblab.project.kitchen_service.event.OrderIsPreparingEvent;
import com.skblab.project.kitchen_service.event.OrderReadyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenEventPub {
    private final RabbitTemplate template;
    private final DirectExchange directExchange;

    public void changeOrderStatusToReady(OrderReadyEvent event) {
        template.convertAndSend(directExchange.getName(), "order.ready", event);
    }

    public void changeOrderStatusToIsPreparing(OrderIsPreparingEvent event) {
        template.convertAndSend(directExchange.getName(), "order.is_preparing", event);
    }
}
