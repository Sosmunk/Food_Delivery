package com.skblab.project.kitchen_service.service;

import com.skblab.project.kitchen_service.event.OrderIsPreparingEvent;
import com.skblab.project.kitchen_service.event.OrderReadyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenService {
    private final KitchenEventPub kitchenEventPub;

    public void changeOrderStatusToIsPreparing(Long orderId) {
        OrderIsPreparingEvent event = OrderIsPreparingEvent.builder()
            .orderId(orderId)
            .build();
        kitchenEventPub.changeOrderStatusToIsPreparing(event);
    }

    public void changeOrderStatusToReady(Long orderId) {
        OrderReadyEvent event = OrderReadyEvent.builder()
            .orderId(orderId)
            .build();
        kitchenEventPub.changeOrderStatusToReady(event);
    }

}
