package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.AccountRequest;
import com.skblab.project.account_service.dto.AuthResponse;
import com.skblab.project.account_service.dto.JwtRequest;
import com.skblab.project.account_service.exception.EmailExistsException;
import com.skblab.project.account_service.exception.PhoneExistsException;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.Role;
import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AccountRequest request) {

        if (phoneExists(request.getPhone())) {
            throw new PhoneExistsException("Телефон " + request.getPhone() + " уже привязан к другому аккаунту!");
        }
        if ((request.getEmail().isBlank() || request.getEmail().isEmpty()) && emailExists(request.getEmail())) {
            throw new EmailExistsException("Email: " + request.getEmail() + " уже привязан к другому аккаунту!");
        }
        Account account = Account.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();
        accountRepository.save(account);

        String jwtToken = jwtService.generateToken(account);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authorize(JwtRequest authRequest) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getPhone(),
                authRequest.getPassword()
            )
        );

        Account account = accountRepository.findAccountByPhone(authRequest.getPhone())
            .orElseThrow(EntityNotFoundException::new);
        String jwtToken = jwtService.generateToken(account);

        return AuthResponse.builder()
            .token(jwtToken)
            .build();
    }

    private boolean phoneExists(String phone) {
        return accountRepository.findAccountByPhone(phone).isPresent();
    }

    private boolean emailExists(String email) {
        return accountRepository.findAccountByEmail(email).isPresent();
    }
}
