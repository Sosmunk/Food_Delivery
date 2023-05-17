package com.skblab.project.account_service.config;

import com.skblab.project.account_service.service.JwtAuthentication;
import com.skblab.project.account_service.service.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
            JwtSecurityContextRepository jwtSecurityContextRepository) throws Exception {

        http = http.csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionAuthenticationStrategy(new JwtSessionAuthenticationStrategy())
                .and()
                .securityContext()
                .securityContextRepository(jwtSecurityContextRepository)
                .and();

        http.authorizeRequests()
                .antMatchers("api/account/**").permitAll()
                .antMatchers("api/account/**").permitAll()
                .anyRequest().anonymous();

        return http.build();
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
}
