package com.example.order_service.rabbit.publisher;

import com.example.order_service.domain.dto.AccountOrderDTO;
import com.example.order_service.domain.dto.DeliveryOrderDTO;
import com.example.order_service.domain.dto.KitchenOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ Publisher сообщений, отправляющий данные о заказе на другие микросервисы
 */
@Component
@RequiredArgsConstructor
public class OrderMessagePublisher {

    private final RabbitTemplate template;
    private final DirectExchange orderDirectExchange;

    /**
     * Метод, отправляющий данные об оплаченном заказе на микросервис кухни
     * @param kitchenOrderDTO {@link KitchenOrderDTO}
     */
    public void sendPaidOrderToKitchen(KitchenOrderDTO kitchenOrderDTO) {
        template.convertAndSend(orderDirectExchange.getName(), "order.to_kitchen", kitchenOrderDTO);
    }

    /**
     * Метод, отправляющий данные о приготовленном заказе на микросервис кухни
     * @param deliveryOrderDTO {@link DeliveryOrderDTO}
     */
    public void sendReadyOrderToDelivery(DeliveryOrderDTO deliveryOrderDTO) {
        template.convertAndSend(orderDirectExchange.getName(), "order.to_delivery", deliveryOrderDTO);
    }

    /**
     * Метод, отправляющий данные об оплаченном заказе на микросервис аккаунтов
     * @param accountOrderDTO {@link AccountOrderDTO}
     */
    public void sendPaidOrderToAccountService(AccountOrderDTO accountOrderDTO) {
        template.convertAndSend(orderDirectExchange.getName(), "order.paid", accountOrderDTO);
    }
}
