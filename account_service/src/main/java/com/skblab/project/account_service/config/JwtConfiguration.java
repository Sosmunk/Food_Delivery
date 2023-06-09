package com.skblab.project.account_service.config;

import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <b>Конфигурация JWT-авторизации c использованием Account в качестве имплементации UserDetails</b>
 * Список бинов:<br>
 * {@link JwtConfiguration#userDetailsService} – <i>Имплементация UserDetailsService с помощью Account</i><br>
 * {@link JwtConfiguration#authenticationProvider} – <i>Имплементация DaoAuthenticationProvider в качестве провайдера для аутентификации</i><br>
 * {@link JwtConfiguration#authenticationManager} – <i>Стандартный менеджер аутентификации</i><br>
 * {@link JwtConfiguration#passwordEncoder} – <i>BCryptEncoder в качестве шифровщика пароля при аутентификации и регистрации</i><br>
 */
@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

    private final AccountRepository accountRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> accountRepository.findAccountByPhone(username)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь c номером телефона " + username + " не найден"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
