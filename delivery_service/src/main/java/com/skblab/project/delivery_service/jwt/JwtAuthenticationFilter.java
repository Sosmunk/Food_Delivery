package com.skblab.project.delivery_service.jwt;

import com.skblab.project.delivery_service.service.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            String token = jwtService.extractJwtToken(request.getHeader("Authorization"));
            Authentication authentication = authenticateToken(token);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }
    private Authentication authenticateToken(String token) {
        try {
            Claims claims = jwtService.extractAllClaims(token);
            String username = claims.getSubject();

            String role = claims.get("role", String.class);

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

            return new UsernamePasswordAuthenticationToken(username, null, List.of(authority));
        } catch (Exception e) {
            logger.error("Validation failed");
            logger.error(e.getMessage());
            logger.error(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }
}
