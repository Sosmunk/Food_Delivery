package com.example.order_service.domain.dto;

import lombok.Value;

import java.util.UUID;

/**
 * DTO для отправки на Account микросервис для накопления и списания бонусов при заказе
 */
@Value
public class AccountOrderDTO {

    /**
     * Id аккаунта
     */
    UUID accountId;

    /**
     * Цена заказа
     */
    Integer orderPrice;

    /**
     * Количество потраченных бонусов (не реализовано из-за нехватки времени)
     */
    Integer spentBonuses;
}
