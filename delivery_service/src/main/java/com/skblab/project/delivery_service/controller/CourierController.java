package com.skblab.project.delivery_service.controller;

import com.skblab.project.delivery_service.dto.CourierRequest;
import com.skblab.project.delivery_service.dto.CourierResponse;
import com.skblab.project.delivery_service.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courier")
public class CourierController {
    private final CourierService courierService;

    @GetMapping("/{courier_id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CourierResponse getCourierById(@PathVariable("courier_id") UUID courierId) {
        return courierService.getCourierById(courierId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourier(@RequestBody CourierRequest request) {
        courierService.createCourier(request);
    }

    @DeleteMapping("/{courier_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourier(@PathVariable("courier_id") UUID courierId) {
        courierService.removeCourier(courierId);
    }
}
