package com.skblab.project.delivery_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Address")
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