package com.skblab.project.account_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>DTO возврата токена доступа при аутентификации</b><br>
 * Список полей:<br>
 * {@link AuthResponse#token} – <i>JWT-токен</i><br>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
