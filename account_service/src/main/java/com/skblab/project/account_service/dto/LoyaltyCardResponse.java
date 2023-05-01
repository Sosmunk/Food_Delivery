package com.skblab.project.account_service.dto;

import com.skblab.project.account_service.model.LoyaltyLevel;
import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCardResponse {
    private Integer bonuses;
    private Double accumulatedAmount;
    private LoyaltyLevel loyaltyLevel;
    private AccountResponse cardholder;
}
