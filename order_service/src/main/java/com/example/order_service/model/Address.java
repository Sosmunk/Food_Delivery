package com.example.order_service.model;

import lombok.*;
import org.springframework.lang.Nullable;

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
@Table(name = "t_address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private UUID addressId;

    @NotNull
    private String city;

    // Район проживания
    @NotNull
    private String district;

    @NotNull
    private String street;

    @NotNull
    private int houseNumber;

    /**
     * Буква номера дома (А, Б, В...)
     */
    @Nullable
    private char houseNumberLiteral;

    @NotNull
    private int apartmentNumber;

}
