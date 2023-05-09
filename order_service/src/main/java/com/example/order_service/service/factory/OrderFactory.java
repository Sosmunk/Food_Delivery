package com.example.order_service.service.factory;


import com.example.order_service.dto.MenuItemDTO;
import com.example.order_service.dto.OrderMenuItemDTO;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.enumerable.orderStatusValue;
import com.example.order_service.model.MenuItem;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderMenuItem;
import com.example.order_service.model.OrderStatus;
import com.example.order_service.repository.MenuItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderFactory {
    MenuItemRepository menuItemRepository;
    public Order createOrderFrom(OrderRequest request) {
        Order order = new Order();
        order.setAddress(request.getAddress());
        order.setOrderMenuItems(request.getOrderMenuItemDTOs()
                .stream()
                .map(orderMenuItemDTO -> createOrderMenuItemFrom(orderMenuItemDTO, order))
                .toList());
        order.setOrderStatus(createPreparingOrderStatus(order));

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
        return menuItemRepository.findMenuItemByName(menuItemDTO.getName());
    }

    public static OrderStatus createPreparingOrderStatus(Order order) {
        return OrderStatus.builder()
                .orderStatusValue(orderStatusValue.PREPARING)
                .order(order)
                .build();
    }
}
