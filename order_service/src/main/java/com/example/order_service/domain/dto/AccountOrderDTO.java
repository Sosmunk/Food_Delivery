package com.example.order_service.domain.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class AccountOrderDTO {

    UUID accountId;

    Integer cost;

    //TODO: Bonuses spent
}
