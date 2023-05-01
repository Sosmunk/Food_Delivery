package com.skblab.project.account_service.repository;

import com.skblab.project.account_service.model.LoyaltyCard;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoyaltyCardRepository extends CrudRepository<LoyaltyCard, UUID> {
    LoyaltyCard findLoyaltyCardByAccount_AccountId(UUID accountId);
    void deleteLoyaltyCardByAccount_AccountId(UUID accountId);
}
