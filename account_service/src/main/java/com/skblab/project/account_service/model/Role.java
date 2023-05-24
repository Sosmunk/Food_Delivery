package com.skblab.project.account_service.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * <b>Роли пользователей</b><br>
 * Список полей:<br>
 * {@link Role#name} – <i>Название роли</i><br>
 */
@RequiredArgsConstructor
@Getter
public enum Role implements GrantedAuthority {

    CUSTOMER("Покупатель"),
    COOK("Повар"),
    COURIER("Курьер"),
    ADMIN("Администратор");

    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
