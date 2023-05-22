package com.example.order_service.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value

public class DeliveryOrderDTO {
    UUID orderId;

    Address address;

    String phone;
    @Value
    @Builder
    public static class Address {
        String city;

        // Район проживания
        String district;

        String street;

        Integer houseNumber;

        /**
         * Буква номера дома (А, Б, В...)
         */
        String houseNumberLiteral;

        Integer apartmentNumber;
    }
}
