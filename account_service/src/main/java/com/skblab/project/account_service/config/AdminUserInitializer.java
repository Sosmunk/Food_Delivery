package com.skblab.project.account_service.config;

import com.skblab.project.account_service.dto.request.AccountRequest;
import com.skblab.project.account_service.repository.AccountRepository;
import com.skblab.project.account_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final AuthService authService;
    @Override
    public void run(String... args){
        if (accountRepository.findAccountByName("admin").isEmpty()) {
            authService.register(new AccountRequest(
                    "admin",
                    "admin",
                    "admin123",
                    "admin@admin.ru",
                    "89089183022",
                    "ADMIN"));
        }
    }
}
