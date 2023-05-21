package com.skblab.project.account_service.dto.request;

import lombok.*;

import javax.validation.constraints.*;

/**
 * <b>DTO для регистрации нового аккаунта</b><br>
 * Список полей:<br>
 * {@link AccountRequest#name} – <i>Имя пользователя</i><br>
 * {@link AccountRequest#surname} – <i>Фамилия пользователя</i><br>
 * {@link AccountRequest#password} – <i>Пароль</i><br>
 * {@link AccountRequest#email} – <i>Email-адрес</i><br>
 * {@link AccountRequest#phone} – <i>Номер телефона</i>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    @NotNull(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия должна содержать от 2 до 50 символов")
    private String surname;

    @NotBlank(message = "Пароль не может быть пустым")
    @NotNull(message = "Пароль не может быть пустым")
    @Size(min = 2, max = 50, message = "Пароль должен содержать от 8 до 50 символов")
//    @Min(value = 8, message = "Пароль должен содержать, как минимум, 8 символов")
//    @Max(value = 50, message = "Пароль не может содержать больше 50 символов")
//    @Pattern.List({
//        @Pattern(regexp = "(?=[A-Z])", message = "Пароль должен содержать, как минимум, одну заглавную латинскую букву"),
//        @Pattern(regexp = "^[a-z]+$", message = "Пароль должен содержать, как минимум, одну строчную латинскую букву"),
//        @Pattern(regexp = "^[0-9]{1,}$", message = "Пароль должен содержать, как минимум, одну цифру"),
//        @Pattern(regexp = "^[@#$%^&+=.\\-_*]$", message = "Пароль должен содержать, как минимум, один специальный символ"),
//    })
    private String password;

    @Email(message = "Неверный формат Email-адреса")
    private String email;

    @Pattern(regexp = "^(\\+7|8)\\d{10}$", message = "Неверный формат телефонного номера")
    private String phone;
}
