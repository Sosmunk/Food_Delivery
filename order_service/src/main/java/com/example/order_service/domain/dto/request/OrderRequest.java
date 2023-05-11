package com.example.order_service.domain.dto.request;

import com.example.order_service.domain.dto.AddressDTO;
import com.example.order_service.domain.dto.OrderMenuItemDTO;
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
