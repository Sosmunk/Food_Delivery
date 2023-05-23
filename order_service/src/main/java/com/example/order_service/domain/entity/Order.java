package com.example.order_service.domain.entity;

import com.example.order_service.domain.enumerable.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Сущность заказа, в которой содержится вся необходимая информация для каждого из микросервисов
 * <p>Хранится в базе данных в таблице <b>t_order</b></p>
 */
@Entity
@Data
@Setter
@Getter
@Table(name = "t_order")
public class Order {
    /**
     * Уникальный id заказа
     */
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    /**
     * ID аккаунта, с которого сделан заказ
     */
    private UUID accountId;

    /**
     * Список позиций в заказе
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderMenuItem> orderMenuItems;

    /**
     * Статус заказа
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    /**
     * Адрес заказа
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Номер телефона, привязанный к учетной записи пользователя
     */
    private String phone;
}
