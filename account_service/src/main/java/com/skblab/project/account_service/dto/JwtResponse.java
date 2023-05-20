package com.skblab.project.account_service.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private static final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
