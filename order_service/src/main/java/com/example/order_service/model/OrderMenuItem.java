package com.example.order_service.model;


import javax.persistence.*;
import java.util.UUID;

// Конкретное блюдо в заказе
@Entity
@Table(name = "t_order_menu_item")
public class OrderMenuItem {

    @Id
    @GeneratedValue
    @Column(name = "order_menu_item_id")
    private UUID orderMenuItemId;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int quantity;
}
