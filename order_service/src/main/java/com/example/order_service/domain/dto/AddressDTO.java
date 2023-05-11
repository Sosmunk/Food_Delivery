package com.example.order_service.domain.dto;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
public class AddressDTO {
    @Size(max=50, message="Слишком длинное название города")
    @NotBlank(message="Не указан город")
    String city;

    @Size(max=255, message="Слишком длинное название района")
    @NotBlank(message="Не указан район проживания")
    String district;

    @Size(max=255, message="Слишком длинное название улицы")
    @NotBlank(message = "Не указана улица")
    String street;

    @Max(message="Слишком большой номер дома", value=9999)
    @NotNull(message = "Не указан номер дома")
    Integer houseNumber;

    /**
     * Буква номера дома (А, Б, В...)
     */
    @Size(max = 1, message="Введено больше одной буквы дома")
    String houseNumberLiteral;

    @Max(message = "Слишком большой номер квартиры", value=9999)
    @NotNull(message = "Не указан номер квартиры")
    Integer apartmentNumber;
}
