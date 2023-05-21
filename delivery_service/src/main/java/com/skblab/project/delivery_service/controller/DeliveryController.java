package com.skblab.project.delivery_service.controller;

import com.skblab.project.delivery_service.model.DeliveryParams;
import com.skblab.project.delivery_service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveryParams> getAllDeliveryParams() {
        return deliveryService.getAllDeliveryParams();
    }

    @GetMapping("/order/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryParams getDeliveryParams(@PathVariable("order_id") UUID orderId) {
        return deliveryService.getDeliveryParams(orderId);
    }

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
