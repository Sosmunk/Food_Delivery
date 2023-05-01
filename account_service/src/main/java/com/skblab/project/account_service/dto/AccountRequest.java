package com.skblab.project.account_service.dto;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
}
