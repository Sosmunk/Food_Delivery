package com.skblab.project.account_service.controller;

import com.skblab.project.account_service.dto.JwtRefreshRequest;
import com.skblab.project.account_service.dto.JwtRequest;
import com.skblab.project.account_service.dto.JwtResponse;
import com.skblab.project.account_service.service.AuthService;
import com.skblab.project.account_service.service.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authorize(@RequestBody JwtRequest request) throws AuthException {
        final JwtResponse token = authService.authorize(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRefreshRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRefreshRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
