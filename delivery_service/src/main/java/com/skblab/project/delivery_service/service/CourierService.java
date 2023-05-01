package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.dto.CourierRequest;
import com.skblab.project.delivery_service.dto.CourierResponse;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    public CourierResponse getCourierById(UUID courierId) {
        return mapToCourierResponse(courierRepository.findById(courierId).orElseThrow(EntityExistsException::new));
    }

    public void createCourier(CourierRequest request) {
        Courier courier = Courier.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .phone(request.getPhone())
            .isAvailable(true)
            .build();
        courierRepository.save(courier);
    }

    public void removeCourier(UUID courierId) {
        courierRepository.deleteById(courierId);
    }

    public Courier getAvailableCourier() {
        return courierRepository.findFirstByIsAvailable(true);
    }

    private CourierResponse mapToCourierResponse(Courier courier) {
        return CourierResponse.builder()
            .name(courier.getName())
            .phone(courier.getPhone())
            .build();
    }
}
