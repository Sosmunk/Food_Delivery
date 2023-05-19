package com.example.order_service.service.factory;


import com.example.order_service.domain.dto.*;
import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.entity.Address;
import com.example.order_service.domain.entity.MenuItem;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.entity.OrderMenuItem;
import com.example.order_service.domain.enumerable.OrderStatus;
import com.example.order_service.repository.MenuItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

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
        //stub
        order.setAccountId(UUID.randomUUID());

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

    public OrderResponse createOrderResponseFrom(Order order) {
        return new OrderResponse(order.getOrderId(),
                order.getOrderMenuItems().stream().map(this::createOrderMenuItemInfo).toList(),
                order.getOrderStatus(),
                order.getAddress());
    }

    public KitchenOrderDTO createKitchenOrderDtoFrom(Order order) {
        return new KitchenOrderDTO(order.getOrderId(), order.getOrderMenuItems()
                .stream()
                .map(this::createKitchenOrderMenuItem).toList());
    }

    public OrderResponse.OrderMenuItemInfo createOrderMenuItemInfo(OrderMenuItem orderMenuItem) {
        return new OrderResponse.OrderMenuItemInfo(createMenuItemInfo(orderMenuItem.getMenuItem()),
                orderMenuItem.getQuantity());
    }

    public OrderResponse.OrderMenuItemInfo.MenuItemInfo createMenuItemInfo(MenuItem menuItem) {
        return new OrderResponse.OrderMenuItemInfo.MenuItemInfo(menuItem.getName(), menuItem.getPrice());
    }

    public KitchenOrderDTO.OrderMenuItem createKitchenOrderMenuItem(OrderMenuItem orderMenuItem) {
        return new KitchenOrderDTO.OrderMenuItem(orderMenuItem.getOrderMenuItemId(),
                orderMenuItem.getMenuItem().getName(),
                orderMenuItem.getQuantity());
    }

    public DeliveryOrderDTO.Address createDeliveryAddress(Address address) {
        return DeliveryOrderDTO.Address.builder()
                .city(address.getCity())
                .district(address.getDistrict())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .houseNumberLiteral(address.getHouseNumberLiteral())
                .apartmentNumber(address.getApartmentNumber())
                .build();
    }

    public DeliveryOrderDTO createDeliveryOrderDTOFrom(Order order) {
        return new DeliveryOrderDTO(order.getOrderId(), createDeliveryAddress(order.getAddress()));
    }
}
