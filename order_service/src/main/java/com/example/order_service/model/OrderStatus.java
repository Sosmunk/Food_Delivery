package com.example.order_service.model;


import com.example.order_service.enumerable.orderStatusValue;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order_status")
public class OrderStatus {
    @Id
    @GeneratedValue
    @Column(name = "order_status_id")
    private UUID orderStatusId;

    private orderStatusValue orderStatusValue;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
