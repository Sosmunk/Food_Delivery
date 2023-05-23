package com.example.order_service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Уникальный предмет (блюдо) в меню определенного заведения.
 * <p>Хранится в базе данных в таблице <b>t_menu_item</b></p>
 * <p>Указывается в {@link OrderMenuItem}</p>
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "t_menu_item")
public class MenuItem {
    /**
     * Уникальный id в меню
     */
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @Column(name = "menu_item_id")
    Long menuItemId;

    /**
     * Название продукта (блюда)
     */
    String name;

    /**
     * Цена
     */
    Integer price;
}
