package com.example.order_service.domain.entity;

import com.example.order_service.domain.enumerable.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private UUID accountId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderMenuItem> orderMenuItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String phone;
}
