package com.example.order_service.domain.entity;

import lombok.*;

import javax.persistence.*;

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

    private String city;

    // Район проживания
    private String district;

    private String street;

    private Integer houseNumber;

    /**
     * Буква номера дома (А, Б, В...)
     */
    private String houseNumberLiteral;

    private Integer apartmentNumber;

}
