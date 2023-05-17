package com.example.order_service.event.publisher;

import com.example.order_service.domain.dto.KitchenOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final RabbitTemplate template;
    private final DirectExchange orderDirectExchange;

    public void sendPaidOrderToKitchen(KitchenOrderDTO kitchenOrderDTO) {
        template.convertAndSend(orderDirectExchange.getName(), "order.paid", kitchenOrderDTO);
    }

}
