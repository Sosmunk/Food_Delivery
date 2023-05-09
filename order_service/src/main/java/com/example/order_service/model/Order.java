package com.example.order_service.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Setter
@Getter
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    @OneToMany(mappedBy = "order")
    private List<OrderMenuItem> orderMenuItems;

    @OneToOne(mappedBy = "order")
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
