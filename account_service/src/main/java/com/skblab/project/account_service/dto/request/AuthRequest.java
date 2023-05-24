package com.skblab.project.account_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>DTO для аутентификации</b><br>
 * Список полей:<br>
 * {@link AuthRequest#password} – <i>Пароль</i><br>
 * {@link AuthRequest#phone} – <i>Номер телефона</i>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    private String password;
    private String phone;
}
