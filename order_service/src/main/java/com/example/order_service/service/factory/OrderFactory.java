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
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

/**
 * Фабрика отвечающая за создание заказов и связанных с ним сущностей
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderFactory {
    MenuItemRepository menuItemRepository;

    /**
     * Метод, создающий сущность заказа для последующей обработки в
     * {@link com.example.order_service.service.OrderService OrderService}
     * @param request {@link OrderRequest}
     * @param claims {@link Claims} полученные из токена, обработанного
     * {@link com.example.order_service.service.JWTService#extractAllClaims(String) JWTService}
     * @return {@link Order}
     */
    public Order createOrderFrom(OrderRequest request, Claims claims) {
        Order order = new Order();
        order.setAddress(createAddressFrom(request.getAddressDTO()));
        order.setOrderMenuItems(request.getOrderMenuItemDTOs()
                .stream()
                .map(orderMenuItemDTO -> createOrderMenuItemFrom(orderMenuItemDTO, order))
                .toList());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setAccountId(UUID.fromString(claims.get("accountId", String.class)));
        order.setPhone(claims.getSubject());

        return order;
    }

    /**
     * Метод, создающий {@link OrderMenuItem}
     * @param orderMenuItemDTO {@link OrderMenuItemDTO}
     * @param order {@link Order}, к которому будет привязан {@link OrderMenuItem}
     * @return {@link OrderMenuItem}
     */
    public OrderMenuItem createOrderMenuItemFrom(OrderMenuItemDTO orderMenuItemDTO, Order order) {
        return OrderMenuItem.builder()
                .menuItem(getMenuItemFrom(orderMenuItemDTO.getMenuItemDTO()))
                .order(order)
                .quantity(orderMenuItemDTO.getQuantity())
                .build();
    }

    /**
     * Метод, создающий {@link MenuItem}, путем поиска в {@link MenuItemRepository} сущности с именем {@link MenuItem#getName() MenuItem.name}
     * <p> Возможно здесь следовало разделить ответственность </p>
     * @param menuItemDTO {@link MenuItemDTO}
     * @return {@link MenuItem}
     */
    public MenuItem getMenuItemFrom(MenuItemDTO menuItemDTO) {
        return Optional.ofNullable(menuItemRepository.findMenuItemByName(menuItemDTO.getName()))
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Продукта %s не существует", menuItemDTO.getName())));
    }

    /**
     * Метод, создающий {@link Address}
     * @param addressDTO {@link AddressDTO}
     * @return {@link Address}
     */
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

    /**
     * Метод, создающий {@link OrderResponse}
     * @param order {@link Order}
     * @return {@link OrderResponse}
     */
    public OrderResponse createOrderResponseFrom(Order order) {
        return new OrderResponse(order.getOrderId(),
                order.getOrderMenuItems().stream().map(this::createOrderMenuItemInfo).toList(),
                order.getOrderStatus(),
                order.getAddress());
    }

    /**
     * Метод, создающий {@link KitchenOrderDTO}
     * @param order {@link Order}
     * @return {@link KitchenOrderDTO}
     */
    public KitchenOrderDTO createKitchenOrderDtoFrom(Order order) {
        return new KitchenOrderDTO(order.getOrderId(), order.getOrderMenuItems()
                .stream()
                .map(this::createKitchenOrderMenuItem).toList());
    }

    /**
     * Метод создающий {@link OrderResponse.OrderMenuItemInfo}
     * @param orderMenuItem {@link OrderMenuItem}
     * @return {@link OrderResponse.OrderMenuItemInfo}
     */
    public OrderResponse.OrderMenuItemInfo createOrderMenuItemInfo(OrderMenuItem orderMenuItem) {
        return new OrderResponse.OrderMenuItemInfo(createMenuItemInfo(orderMenuItem.getMenuItem()),
                orderMenuItem.getQuantity());
    }

    /**
     * Метод создающий {@link OrderResponse.OrderMenuItemInfo.MenuItemInfo}
     * @param menuItem {@link MenuItem}
     * @return {@link OrderResponse.OrderMenuItemInfo.MenuItemInfo}
     */
    public OrderResponse.OrderMenuItemInfo.MenuItemInfo createMenuItemInfo(MenuItem menuItem) {
        return new OrderResponse.OrderMenuItemInfo.MenuItemInfo(menuItem.getName(), menuItem.getPrice());
    }

    /**
     * Метод создающий {@link KitchenOrderDTO.OrderMenuItem}
     * @param orderMenuItem {@link OrderMenuItem}
     * @return {@link KitchenOrderDTO.OrderMenuItem}
     */
    public KitchenOrderDTO.OrderMenuItem createKitchenOrderMenuItem(OrderMenuItem orderMenuItem) {
        return new KitchenOrderDTO.OrderMenuItem(orderMenuItem.getOrderMenuItemId(),
                orderMenuItem.getMenuItem().getName(),
                orderMenuItem.getQuantity());
    }

    /**
     * Метод создающий {@link DeliveryOrderDTO.Address}
     * @param address {@link Address}
     * @return {@link DeliveryOrderDTO.Address}
     */
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

    /**
     * Метод создающий {@link DeliveryOrderDTO}
     * @param order {@link Order}
     * @return {@link DeliveryOrderDTO}
     */
    public DeliveryOrderDTO createDeliveryOrderDTOFrom(Order order) {
        return new DeliveryOrderDTO(order.getOrderId(), createDeliveryAddress(order.getAddress()), order.getPhone());
    }
}
