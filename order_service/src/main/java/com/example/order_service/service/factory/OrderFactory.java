package com.example.order_service.service.factory;


import com.example.order_service.dto.AddressDTO;
import com.example.order_service.dto.MenuItemDTO;
import com.example.order_service.dto.OrderMenuItemDTO;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.enumerable.OrderStatus;
import com.example.order_service.model.*;
import com.example.order_service.repository.MenuItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderFactory {
    MenuItemRepository menuItemRepository;
    public Order createOrderFrom(OrderRequest request) {
        Order order = new Order();
        order.setAddress(createAddressFrom(request.getAddressDTO()));
        order.setOrderMenuItems(request.getOrderMenuItemDTOs()
                .stream()
                .map(orderMenuItemDTO -> createOrderMenuItemFrom(orderMenuItemDTO, order))
                .toList());
        order.setOrderStatus(OrderStatus.CREATED);

        return order;
    }

    public OrderMenuItem createOrderMenuItemFrom(OrderMenuItemDTO orderMenuItemDTO, Order order) {
        return OrderMenuItem.builder()
                .menuItem(createMenuItemFrom(orderMenuItemDTO.getMenuItemDTO()))
                .order(order)
                .quantity(orderMenuItemDTO.getQuantity())
                .build();
    }

    public MenuItem createMenuItemFrom(MenuItemDTO menuItemDTO) {
        return Optional.ofNullable(menuItemRepository.findMenuItemByName(menuItemDTO.getName()))
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Продукта %s не существует", menuItemDTO.getName())));
    }

    public static Address createAddressFrom(AddressDTO addressDTO) {
        return Address.builder()
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .district(addressDTO.getDistrict())
                .houseNumber(addressDTO.getHouseNumber())
                .houseNumberLiteral(addressDTO.getHouseNumberLiteral())
                .apartmentNumber(addressDTO.getApartmentNumber())
                .build();

    }
}
