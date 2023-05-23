package com.example.order_service.domain.dto.request;

import com.example.order_service.domain.dto.AddressDTO;
import com.example.order_service.domain.dto.OrderMenuItemDTO;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Тело JSON запроса для {@link com.example.order_service.web.controller.OrderController#postOrder(OrderRequest, String) OrderController#postOrder}
 *
 * <p>Пример:</p>
 * <pre>
 * {
 *   "addressDTO": {
 *     "city": "London",
 *     "district": "something",
 *     "street": "1st street",
 *     "houseNumber": "1",
 *     "houseNumberLiteral": "A",
 *     "apartmentNumber": "13"
 *   },
 *   "orderMenuItemDTOs": [
 *     {
 *       "menuItemDTO": {
 *         "name": "cabbage"
 *       },
 *       "quantity": 1
 *     },
 *     {
 *       "menuItemDTO": {
 *         "name": "tomato"
 *       },
 *       "quantity": 2
 *     }
 *   ]
 * }
 * </pre>
 */
@Value
public class OrderRequest {
    /**
     * {@link AddressDTO} адрес доставки
     */
    @NotNull(message="Не указан адрес")
    @Valid
    AddressDTO addressDTO;

    /**
     * {@link OrderMenuItemDTO} позиции блюд
     */
    @NotNull(message="Заказ пуст")
    @Valid
    List<OrderMenuItemDTO> orderMenuItemDTOs;

}
