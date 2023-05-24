package com.skblab.project.kitchen_service.service;

import com.skblab.project.kitchen_service.aspect.LogOrderStatusChange;
import com.skblab.project.kitchen_service.event.OrderIsPreparingEvent;
import com.skblab.project.kitchen_service.event.OrderPaidEvent;
import com.skblab.project.kitchen_service.event.OrderReadyEvent;
import com.skblab.project.kitchen_service.model.KitchenParams;
import com.skblab.project.kitchen_service.repository.KitchenParamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KitchenService {
    private final KitchenEventPub kitchenEventPub;
    private final KitchenParamsRepository kitchenParamsRepository;

    @LogOrderStatusChange
    public void changeOrderStatusToIsPreparing(UUID orderId) {
        OrderIsPreparingEvent event = OrderIsPreparingEvent.builder()
            .orderId(orderId)
            .build();
        kitchenEventPub.changeOrderStatusToIsPreparing(event);
    }

    @LogOrderStatusChange
    public void changeOrderStatusToReady(UUID orderId) {
        OrderReadyEvent event = OrderReadyEvent.builder()
            .orderId(orderId)
            .build();
        kitchenEventPub.changeOrderStatusToReady(event);
        removeKitchenParams(orderId);
    }

    public KitchenParams getKitchenParams(UUID orderId) {
        return kitchenParamsRepository.findById(orderId).orElseThrow();
    }

    public void createKitchenParams(OrderPaidEvent event) {
        KitchenParams deliveryParams = KitchenParams.builder()
            .orderId(event.getOrderId())
            .orderMenuItems(event.getOrderMenuItems())
            .build();
        kitchenParamsRepository.save(deliveryParams);
    }

    public void removeKitchenParams(UUID orderId) {
        kitchenParamsRepository.deleteById(orderId);
    }

    public List<KitchenParams> getAllKitchenParams() {
        List<KitchenParams> result = new ArrayList<>();
        kitchenParamsRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }
}
