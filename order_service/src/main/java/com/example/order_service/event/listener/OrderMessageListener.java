package com.example.order_service.event.listener;

import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.event.OrderInDeliveryEvent;
import com.example.order_service.event.OrderPreparingEvent;
import com.example.order_service.event.OrderReadyEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class OrderMessageListener {

    private final OrderService orderService;

    @RabbitListener(queues = "ready_order_queue")
    public void handleReadyOrder(OrderReadyEvent event) {
        orderService.processReadyOrder(event.getOrderId());
    }

    @RabbitListener(queues = "is_preparing_order_queue")
    public void handlePreparingOrder(OrderPreparingEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.PREPARING);
    }

    @RabbitListener(queues = "in_delivery_order_queue")
    public void handleOrderInDelivery(OrderInDeliveryEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.IN_DELIVERY);
    }

    // TODO

//    @RabbitListener(queues= "delivered_order_queue")
//    public void handleOrderDelivered(OrderDelivered event) {
//
//    }

}
