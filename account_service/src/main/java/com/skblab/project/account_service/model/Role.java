package com.skblab.project.account_service.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
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
