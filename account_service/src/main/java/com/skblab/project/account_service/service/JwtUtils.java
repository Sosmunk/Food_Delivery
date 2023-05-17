package com.skblab.project.account_service.service;

import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    @Value("${jwt.access_token_validity}")
    private Long accessTokenValidity;

    @Value("${jwt.refresh_token_validity}")
    private Long refreshTokenValidity;

    @Value("${jwt.access_secret}")
    private String accessSecret;

    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    public Cookie generateCookie(Account account) {
        String token = generateAccessToken(account);
        Cookie jwtCookie = new Cookie("JSESSIONID", token);
        jwtCookie.setMaxAge((int) (accessTokenValidity * 1000));
        return jwtCookie;
    }

    public String generateAccessToken(Account account) {
        Claims claims = Jwts.claims().setSubject(account.getName());
        claims.put("uuid", account.getAccountId() + "");
        claims.put("role", account.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, accessSecret)
                .compact();
    }

    public String generateRefreshToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS256, refreshSecret)
                .compact();
    }

    public Account parseAccessToken(String token) {
        try {
            Claims body = Jwts.parser()
                .setSigningKey(accessSecret)
                .parseClaimsJws(token)
                .getBody();

            Account account = new Account();
            account.setAccountId(UUID.fromString(body.get("uuid").toString()));
            account.setName(body.getSubject());
            account.setPhone(body.get("phone").toString());
            account.setRole(Role.valueOf(body.get("role").toString()));

            return account;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }
}
