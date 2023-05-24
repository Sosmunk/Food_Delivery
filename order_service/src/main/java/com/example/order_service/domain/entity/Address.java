package com.example.order_service.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Адрес, хранящийся в таблице <b>t_address</b>
 * <p>Указывается в {@link com.example.order_service.domain.entity.Order Order}</p>
 */
@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_address")
public class Address {
    /**
     * Уникальный id адреса доставки
     */
    @Id
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @Column(name = "address_id")
    private Long addressId;
    /**
     * Город
     */
    private String city;

    /**
     * Район
     */
    private String district;

    /**
     * Улица
     */
    private String street;

    /**
     * Номер дома
     */
    private Integer houseNumber;

    /**
     * Буква номера дома (А, Б, В...)
     */
    private String houseNumberLiteral;

    /**
     * Номер квартиры
     */
    private Integer apartmentNumber;

}
