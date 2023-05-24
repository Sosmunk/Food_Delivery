package com.example.order_service.rabbit.listener;

import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.rabbit.event.OrderDeliveredEvent;
import com.example.order_service.rabbit.event.OrderInDeliveryEvent;
import com.example.order_service.rabbit.event.OrderPreparingEvent;
import com.example.order_service.rabbit.event.OrderReadyEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Обработчик сообщений RabbitMQ, принимающий информацию о статусе заказа из других микросервисов
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class OrderMessageListener {

    private final OrderService orderService;

    /**
     * Обработка сообщения о том, что заказ был приготовлен
     * @param event {@link OrderReadyEvent}
     */
    @RabbitListener(queues = "ready_order_queue")
    public void handleReadyOrder(OrderReadyEvent event) {
        orderService.processReadyOrder(event.getOrderId());
    }

    /**
     * Обработка сообщения о том, что заказ готовится
     * @param event {@link OrderPreparingEvent}
     */
    @RabbitListener(queues = "is_preparing_order_queue")
    public void handlePreparingOrder(OrderPreparingEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.PREPARING);
    }

    /**
     * Обработка сообщения о том, что заказ был передан в доставку
     * @param event {@link OrderInDeliveryEvent}
     */
    @RabbitListener(queues = "in_delivery_order_queue")
    public void handleOrderInDelivery(OrderInDeliveryEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.IN_DELIVERY);
    }

    /**
     * Обработка сообщения о том, что заказ был успешно доставлен
     * @param event {@link OrderDeliveredEvent}
     */
    @RabbitListener(queues= "delivered_order_queue")
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        orderService.changeOrderStatus(event.getOrderId(), OrderStatus.DELIVERED);
        log.info("ЗАКАЗ ДОСТАВЛЕН УРРРРРРААААААА!!!!!!!!");
    }

}
