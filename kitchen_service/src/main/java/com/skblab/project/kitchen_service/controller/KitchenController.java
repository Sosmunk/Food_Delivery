package com.skblab.project.kitchen_service.controller;

import com.skblab.project.kitchen_service.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenService kitchenService;

    @PatchMapping("/{order_id}/is_preparing")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatusToIsPreparing(@PathVariable("order_id") Long orderId) {
        kitchenService.changeOrderStatusToIsPreparing(orderId);
    }

    @PatchMapping("/{order_id}/ready")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatusToReady(@PathVariable("order_id") Long orderId) {
        kitchenService.changeOrderStatusToIsPreparing(orderId);
    }
}
