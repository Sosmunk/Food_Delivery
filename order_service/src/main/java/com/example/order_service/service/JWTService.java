package com.example.order_service.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

/**
 * Сервис, отвечающий за обработку поступающих JWT токенов
 */
@Service
public interface JWTService {

    /**
     * Извлекает все Claims из JWT токена
     * @param token token без "Bearer "
     * @return {@link Claims}
     */
    Claims extractAllClaims(String token);

    /**
     * Извлекает JWT токен из "Authorization" header
     * @param token bearer token
     * @return JWT token без "Bearer "
     */
    String extractJwtToken(String token);
}
