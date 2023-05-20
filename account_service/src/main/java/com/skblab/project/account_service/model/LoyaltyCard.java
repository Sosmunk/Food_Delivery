package com.skblab.project.account_service.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_loyalty_card")
public class LoyaltyCard implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private UUID cardId;

    @OneToOne
    @JoinColumn(name = "account_id")
    @MapsId
    private Account account;

    private Integer bonuses;

    @Column(name = "accumulated_amount")
    private Double accumulatedAmount;

    @Enumerated(EnumType.STRING)
    private LoyaltyLevel level;

    @PreRemove
    private void removeAccount() {
        account.setLoyaltyCard(null);
    }
}
