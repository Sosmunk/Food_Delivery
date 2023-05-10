package com.example.order_service.dto.request;

import com.example.order_service.dto.AddressDTO;
import com.example.order_service.dto.OrderMenuItemDTO;
import lombok.Value;

import java.util.List;

@Value
public class OrderRequest {
    AddressDTO addressDTO;
    List<OrderMenuItemDTO> orderMenuItemDTOs;

}
