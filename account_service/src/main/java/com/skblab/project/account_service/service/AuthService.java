package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.JwtRequest;
import com.skblab.project.account_service.dto.JwtResponse;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.repository.AccountRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final HashMap<String, String> tokenStorage = new HashMap<>();

    public JwtResponse authorize(JwtRequest authRequest) throws AuthException {
        Account account = accountRepository.findAccountByPhone(authRequest.getPhone());
        if (account.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(account);
            final String refreshToken = jwtProvider.generateRefreshToken(account);
            tokenStorage.put(account.getPhone(), refreshToken);
            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new AuthException("Нерпавильный пароль!");
        }
    }

    public JwtResponse getAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String phone = claims.get("phone").toString();
            final String savedRefreshToken = tokenStorage.get(phone);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final Account account = accountRepository.findAccountByPhone(phone);
                final String accessToken = jwtProvider.generateAccessToken(account);
                return JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(null)
                        .build();
            }
        }
        return JwtResponse.builder()
                .accessToken(null)
                .refreshToken(null)
                .build();
    }

    public JwtResponse refresh(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getAccessClaims(refreshToken);
            final String phone = claims.get("phone").toString();
            final String savedRefreshToken = tokenStorage.get(phone);
            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final Account account = accountRepository.findAccountByPhone(phone);
                final String accessToken = jwtProvider.generateAccessToken(account);
                final String newRefreshToken = jwtProvider.generateRefreshToken(account);
                tokenStorage.put(phone, newRefreshToken);
                return JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
            }
        }
        return JwtResponse.builder()
                .accessToken(null)
                .refreshToken(null)
                .build();
    }
}
