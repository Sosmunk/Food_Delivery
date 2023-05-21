package com.skblab.project.account_service.jwt;

import com.skblab.project.account_service.model.LoyaltyCard;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * <b>Сервис генерации и парсинга JWT-токенов</b>
 */
@Service
@Log4j2
public class JwtService {

    @Value("${jwt.access_secret}")
    private String secret;

    @Value("#{${jwt.access_token_validity}}")
    private Long tokenValidity;

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
    ) {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * tokenValidity))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String phone = extractUsername(token);
        if (isTokenExpired(token)) return false;
        return phone.equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private UUID extractAccountId(String token) {
        return UUID.fromString(extractAllClaims(token).get("accountId", String.class));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }
}
