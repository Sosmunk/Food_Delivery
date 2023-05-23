package com.example.order_service.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

/**
 * Конкретная позиция в меню, привязанная к определеному заказу.
 * <p>Хранится в базе данных в таблице <b>t_order_menu_item</b></p>
 * <p>Указывается в {@link Order}</p>
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order_menu_item")
public class OrderMenuItem {

    /**
     * Уникальный id позиции в заказе
     */
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @Column(name = "order_menu_item_id")
    private Long orderMenuItemId;

    /**
     * Предмет в меню, на который ссылается данная сущность
     */
    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    /**
     * Заказ, в котором содержится данная сущность
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @JsonBackReference
    public Order getOrder() {
        return order;
    }

    /**
     * Количество предметов данной позиции
     */
    private Integer quantity;
}
