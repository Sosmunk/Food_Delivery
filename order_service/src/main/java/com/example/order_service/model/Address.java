package com.example.order_service.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_address")
public class Address {

    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @Column(name = "address_id")
    private Long addressId;

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
