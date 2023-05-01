package com.skblab.project.account_service.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private UUID accountId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Email
    private String email;

    private String phone;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private LoyaltyCard loyaltyCard;
}
