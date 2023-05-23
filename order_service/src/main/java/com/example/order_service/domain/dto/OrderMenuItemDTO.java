package com.example.order_service.domain.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO предмета в заказе, содержащее блюдо из меню, и его количество.
 * Указывается в
 * {@link com.example.order_service.domain.dto.request.OrderRequest OrderRequest}
 */
@Value
public class OrderMenuItemDTO {
    /**
     * Продукт/блюдо, содержащееся в меню
     */
    @Valid
    @NotNull(message="Не указан продукт")
    MenuItemDTO menuItemDTO;


    /**
     * Количество продукта
     */
    @Min(value=1, message="Указано меньше одного продукта")
    @Max(value=99, message="Указано слишком много продуктов")
    @NotNull(message = "Не указано количество продукта")
    Integer quantity;
}
