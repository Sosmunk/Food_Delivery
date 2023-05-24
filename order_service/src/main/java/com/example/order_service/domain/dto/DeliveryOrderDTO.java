package com.example.order_service.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * DTO для отправки на Delivery микросервис с информацией об адресе заказа и телефоном пользователя
 */
@Value
public class DeliveryOrderDTO {
    /**
     * Номер заказа
     */
    UUID orderId;

    /**
     * Адрес заказа
     */
    Address address;

    /**
     * Номер телефона с которого совершен заказ
     */
    String phone;
    @Value
    @Builder
    public static class Address {
        /**
         * Город
         */
        String city;

        /**
         * Район
         */
        String district;

        /**
         * Улица
         */
        String street;

        /**
         * Номер дома
         */
        Integer houseNumber;

        /**
         * Буква номера дома (А, Б, В...)
         */
        String houseNumberLiteral;

        /**
         * Номер квартиры
         */
        Integer apartmentNumber;
    }
}
