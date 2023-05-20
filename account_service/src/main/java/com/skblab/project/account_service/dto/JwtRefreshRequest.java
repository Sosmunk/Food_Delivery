package com.skblab.project.account_service.dto;

import lombok.Data;

@Data
public class JwtRefreshRequest {
    private String refreshToken;
}
