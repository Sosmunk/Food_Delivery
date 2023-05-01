package com.example.order_service.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "t_menu_item")
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "menu_item_id")
    private UUID menuItemId;
    private String name;
}
