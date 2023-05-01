package com.example.order_service.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

//    @ManyToOne
//    @JoinColumn("account_id")
//    private Account account;

    @OneToMany(mappedBy = "order")
    private List<OrderMenuItem> orderMenuItems;

    @OneToOne(mappedBy = "order")
    private OrderStatus orderStatus;

}
