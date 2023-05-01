package com.skblab.project.account_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
