package com.skblab.project.delivery_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Id
    private Long addressId;
    private String city;
    private String district;
    private String street;
    private Integer houseNumber;
    private String houseNumberLiteral;
    private Integer apartmentNumber;
}