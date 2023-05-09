package com.example.order_service.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_menu_item")
public class MenuItem {
    @Id
    @GeneratedValue
    @Column(name = "menu_item_id")
    UUID menuItemId;
    String name;
    Integer price;
}
