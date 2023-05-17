package com.skblab.project.account_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRefreshRequest {
    private String refreshToken;
}
