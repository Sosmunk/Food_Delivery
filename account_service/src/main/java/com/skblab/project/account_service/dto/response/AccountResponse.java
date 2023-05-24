package com.skblab.project.account_service.dto.response;

import lombok.*;

/**
 * <b>DTO представления аккаунта</b><br>
 * Список полей:<br>
 * {@link AccountResponse#name} – <i>Имя пользователя</i><br>
 * {@link AccountResponse#surname} – <i>Фамилия пользователя</i><br>
 * {@link AccountResponse#email} – <i>Email-адрес</i><br>
 * {@link AccountResponse#phone} – <i>Номер телефона</i><br>
 * {@link AccountResponse#loyaltyCard} – <i>Карта лояльности</i>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LoyaltyCardResponse loyaltyCard;
}
