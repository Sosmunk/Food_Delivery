package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.request.AccountRequest;
import com.skblab.project.account_service.dto.response.AuthResponse;
import com.skblab.project.account_service.dto.request.AuthRequest;
import com.skblab.project.account_service.exception.EmailExistsException;
import com.skblab.project.account_service.exception.PhoneExistsException;
import com.skblab.project.account_service.jwt.JwtService;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.Role;
import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация нового аккаунта
     */
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

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("accountId", account.getAccountId());

        String jwtToken = jwtService.generateToken(extraClaims, account);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Авторизация с помощью JWT-токена
     */
    public AuthResponse authorize(AuthRequest authRequest) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getPhone(),
                authRequest.getPassword()
            )
        );

        Account account = accountRepository.findAccountByPhone(authRequest.getPhone())
            .orElseThrow(EntityNotFoundException::new);

        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("accountId", account.getAccountId());

        String jwtToken = jwtService.generateToken(extraClaims, account);

        return AuthResponse.builder()
            .token(jwtToken)
            .build();
    }

    /**
     * Проверка существования номера телефона в БД
     */
    private boolean phoneExists(String phone) {
        return accountRepository.findAccountByPhone(phone).isPresent();
    }

    /**
     * Проверка существования Email-адреса в БД
     */
    private boolean emailExists(String email) {
        return accountRepository.findAccountByEmail(email).isPresent();
    }
}
