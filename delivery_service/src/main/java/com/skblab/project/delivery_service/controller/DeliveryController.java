package com.skblab.project.delivery_service.controller;

import com.skblab.project.delivery_service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PatchMapping("/order/{order_id}/in_delivery")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatusToInDelivery(@PathVariable("order_id") UUID orderId) {
        deliveryService.changeOrderStatusToInDelivery(orderId);
    }

    @PatchMapping("/order/{order_id}/delivered")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatusToDelivered(@PathVariable("order_id") UUID orderId) {
        deliveryService.changeOrderStatusToDelivered(orderId);
    }
}
