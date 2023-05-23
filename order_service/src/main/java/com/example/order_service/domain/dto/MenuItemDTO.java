package com.example.order_service.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO предмета, находящегося в меню, указываемое в {@link com.example.order_service.domain.dto.OrderMenuItemDTO}
 */
@Value
public class MenuItemDTO {
    /**
     * Название продукта
     */
    @Size(max=255, message="Слишком длинное название продукта")
    @NotBlank(message = "Не указано имя продукта")
    String name;

    @JsonCreator
    public MenuItemDTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
