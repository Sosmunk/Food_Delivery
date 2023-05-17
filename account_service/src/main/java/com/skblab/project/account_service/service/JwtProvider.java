package com.skblab.project.account_service.service;

import com.skblab.project.account_service.model.Account;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.Instant;
import java.util.Date;

@Service
@Log4j2
public class JwtProvider {
    @Value("${jwt.access_token_validity}")
    private Long accessTokenValidity;

    @Value("${jwt.refresh_token_validity}")
    private Long refreshTokenValidity;

    @Value("${jwt.access_secret}")
    private String accessSecret;

    @Value("${jwt.refresh_secret}")
    private String refreshSecret;

    public String generateAccessToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS256, accessSecret)
                .claim("uuid", account.getAccountId())
                .claim("name", account.getName())
                .claim("phone", account.getPhone())
                .claim("role", account.getRole())
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

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, accessSecret);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, refreshSecret);
    }

    private boolean validateToken(String token, String secret) {
        try {
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException exc) {
            log.error("Token expired", exc);
        } catch (UnsupportedJwtException exc) {
            log.error("Unsupported jwt", exc);
        } catch (MalformedJwtException exc) {
            log.error("Malformed jwt", exc);
        } catch (SignatureException exc) {
            log.error("Invalid signature", exc);
        } catch (Exception exc) {
            log.error("invalid token", exc);
        }
        return false;
    }

    public Claims getAccessClaims(String token) {
        return getClaims(token, accessSecret);
    }

    public Claims getRefreshClaims(String token) {
        return getClaims(token, refreshSecret);
    }

    private Claims getClaims(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
