package com.example.order_service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class MenuItemDTO {
    String name;

    @JsonCreator
    public MenuItemDTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}
