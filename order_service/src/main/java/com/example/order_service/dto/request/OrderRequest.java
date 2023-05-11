package com.example.order_service.dto.request;

import com.example.order_service.dto.AddressDTO;
import com.example.order_service.dto.OrderMenuItemDTO;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class OrderRequest {
    @NotNull(message="Не указан адрес")
    @Valid
    AddressDTO addressDTO;

    @NotNull(message="Заказ пуст")
    @Valid
    List<OrderMenuItemDTO> orderMenuItemDTOs;

}
