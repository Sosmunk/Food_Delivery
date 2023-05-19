package com.skblab.project.delivery_service.handler;

import com.skblab.project.delivery_service.event.OrderToDeliveryEvent;
import com.skblab.project.delivery_service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderReadyHandler {
    private final DeliveryService deliveryService;

    @RabbitListener(queues = "to_delivery_order_queue")
    public void handleOrderReady(OrderToDeliveryEvent event) {
        deliveryService.changeOrderStatusToInDelivery(event.getOrderId(), event.getAddress());
    }
}
