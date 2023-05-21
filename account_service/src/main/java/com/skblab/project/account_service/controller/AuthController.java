package com.skblab.project.account_service.controller;

import com.skblab.project.account_service.dto.request.AccountRequest;
import com.skblab.project.account_service.dto.request.AuthRequest;
import com.skblab.project.account_service.dto.response.AuthResponse;
import com.skblab.project.account_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <b>Контроллер для регистрации нового аккаунта и авторизации</b>
 */
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authorize(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authorize(request));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AccountRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
