package com.skblab.project.account_service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtResponse {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
