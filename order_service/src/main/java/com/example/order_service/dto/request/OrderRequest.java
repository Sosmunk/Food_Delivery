package com.example.order_service.dto.request;

import com.example.order_service.dto.OrderMenuItemDTO;
import com.example.order_service.model.Address;
import lombok.Value;

import java.util.List;

@Value
public class OrderRequest {
    Address address;
    List<OrderMenuItemDTO> orderMenuItemDTOs;

}
