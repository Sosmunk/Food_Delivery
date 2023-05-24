package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.event.OrderDeliveredEvent;
import com.skblab.project.delivery_service.event.OrderInDeliveryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryEventPub {
    private final RabbitTemplate template;
    private final DirectExchange directExchange;

    public void changeOrderStatusToInDelivery(OrderInDeliveryEvent event) {
        template.convertAndSend(directExchange.getName(), "order.in_delivery", event);
    }

    public void changeOrderStatusToDelivered(OrderDeliveredEvent event) {
        template.convertAndSend(directExchange.getName(), "order.delivered", event);
    }
}
