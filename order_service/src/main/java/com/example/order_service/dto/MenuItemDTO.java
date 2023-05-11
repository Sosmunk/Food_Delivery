package com.example.order_service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class MenuItemDTO {
    @Size(max=255, message="Слишком длинное название продукта")
    @NotBlank(message = "Не указано имя продукта")
    String name;

    @JsonCreator
    public MenuItemDTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
