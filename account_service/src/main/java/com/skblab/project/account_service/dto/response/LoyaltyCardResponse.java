package com.skblab.project.account_service.dto.response;

import com.skblab.project.account_service.model.LoyaltyLevel;
import lombok.*;

/**
 * <b>DTO представления карты лояльности</b><br>
 * Список полей:<br>
 * {@link LoyaltyCardResponse#bonuses} – <i>Накопленные бонусы на карте</i><br>
 * {@link LoyaltyCardResponse#accumulatedAmount} – <i>Накопленная сумма платажей</i><br>
 * {@link LoyaltyCardResponse#loyaltyLevel} – <i>Уровень лояльности</i><br>
 * {@link LoyaltyCardResponse#cardholder} – <i>Владелец карты</i>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCardResponse {
    private Integer bonuses;
    private Double accumulatedAmount;
    private LoyaltyLevel loyaltyLevel;
    private AccountResponse cardholder;
}
