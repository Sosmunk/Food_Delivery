package com.example.order_service.dto;

import lombok.Value;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Value
public class AddressDTO {

    @NotNull
    String city;

    // Район проживания
    @NotNull
    String district;

    @NotNull
    String street;

    @NotNull
    int houseNumber;

    /**
     * Буква номера дома (А, Б, В...)
     */
    @Nullable
    char houseNumberLiteral;

    @NotNull
    int apartmentNumber;
}
