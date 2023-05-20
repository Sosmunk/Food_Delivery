package com.skblab.project.account_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LoyaltyCardResponse loyaltyCard;
}
