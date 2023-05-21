package com.example.order_service.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public interface JWTService {

    Claims extractAllClaims(String token);

    String extractJwtToken(String token);
}
