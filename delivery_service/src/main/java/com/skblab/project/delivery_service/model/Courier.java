package com.skblab.project.delivery_service.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_courier")
public class Courier {
    @Id
    @GeneratedValue
    @Column(name = "courier_id")
    private UUID courierId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String phone;

    private Long orderId;

    @NotNull
    @Column(name = "is_available")
    private Boolean isAvailable;
}
