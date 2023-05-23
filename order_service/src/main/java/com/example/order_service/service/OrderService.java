package com.example.order_service.service;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.domain.entity.Order;
import com.example.order_service.domain.enumerable.OrderStatus;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.UUID;

/**
 * Сервис отвечающий за обработку всех данных о заказах
 */
public interface OrderService {

    /**
     * Помещает заказ в базу данных
     * @param orderRequest JSON тело запроса {@link OrderRequest}
     * @param claims полученные из токена
     * @return {@link OrderResponse}
     */
    OrderResponse placeOrder(OrderRequest orderRequest, Claims claims);

    /**
     * Изменяет статус заказа, отправляет событие
     * @param uuid id заказа {@link Order#getOrderId() orderId}
     * @param orderStatus статус заказа {@link OrderStatus}
     */
    void changeOrderStatus(UUID uuid, OrderStatus orderStatus);

    /**
     * Обрабатывает готовый заказ и отправляет его в доставку
     * @param orderId id заказа {@link Order#getOrderId() orderId}
     */
    void processReadyOrder(UUID orderId);

    /**
     * Считает цену заказа по сумме цен его позиций
     * @param order заказ {@link Order}
     * @return цена заказа
     */
    Integer calculateOrderPrice(Order order);

    /**
     * Получает все заказы привязанные к определенному аккаунту
     * @param accountId id аккаунта
     * @return List&lt;{@link OrderResponse}&gt;
     */
    List<OrderResponse> getAccountOrders(UUID accountId);
}
