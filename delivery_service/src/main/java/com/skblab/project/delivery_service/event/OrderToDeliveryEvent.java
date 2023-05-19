package com.skblab.project.delivery_service.event;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
public class OrderToDeliveryEvent {
    UUID orderId;

    Address address;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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
