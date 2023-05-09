package com.example.order_service.dto;

import lombok.Value;

@Value
public class OrderMenuItemDTO {

    MenuItemDTO menuItemDTO;

    Integer quantity;
}
