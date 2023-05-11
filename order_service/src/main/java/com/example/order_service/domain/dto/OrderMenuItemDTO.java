package com.example.order_service.domain.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class OrderMenuItemDTO {
    @Valid
    @NotNull(message="Не указан продукт")
    MenuItemDTO menuItemDTO;

    @Min(value=1, message="Указано меньше одного продукта")
    @Max(value=99, message="Указано слишком много продуктов")
    @NotNull(message = "Не указано количество продукта")
    Integer quantity;
}
