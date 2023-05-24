package com.skblab.project.account_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <b>Уровени лояльности</b><br>
 * Список полей:<br>
 * {@link LoyaltyLevel#description} – <i>Название уровня</i><br>
 * {@link LoyaltyLevel#requiredOrdersSum} – <i>Необходимая накопленная сумма</i><br>
 * {@link LoyaltyLevel#bonusesPercent} – <i>Процент начисления бонусов</i><br>
 */
@RequiredArgsConstructor
@Getter
public enum LoyaltyLevel {
    COMMON("Обычный", 0, 1),
    BRONZE("Бронзовый", 1000, 2),
    SILVER("Серебряный", 5000, 3),
    GOLD("Золотой", 20000, 5),
    PLATINUM("Платиновый", 100000, 10);

    private final String description;
    private final Integer requiredOrdersSum;
    private final Integer bonusesPercent;
}
