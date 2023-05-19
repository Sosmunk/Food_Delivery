package com.example.order_service.event.listener;

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
        try {
            orderService.processReadyOrder(event.getOrderId());
        }
        catch (Exception ex){
            log.error(ex);
        }

    }
}
