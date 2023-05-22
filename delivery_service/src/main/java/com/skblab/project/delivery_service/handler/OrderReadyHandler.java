package com.skblab.project.delivery_service.handler;

import com.skblab.project.delivery_service.event.OrderToDeliveryEvent;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.model.DeliveryParams;
import com.skblab.project.delivery_service.service.CourierService;
import com.skblab.project.delivery_service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReadyHandler {
    private final DeliveryService deliveryService;
    private final CourierService courierService;

    @Transactional
    @RabbitListener(queues = "to_delivery_order_queue")
    public void handleOrderReady(OrderToDeliveryEvent event) {
        Courier courier = courierService.getAvailableCourier();
        deliveryService.createDeliveryParams(event);
        if (courier != null) {
            deliveryService.changeOrderStatusToInDelivery(event.getOrderId(), courier);
        }
    }
}
