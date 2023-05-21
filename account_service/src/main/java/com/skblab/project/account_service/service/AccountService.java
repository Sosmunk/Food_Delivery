package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.response.AccountResponse;
import com.skblab.project.account_service.dto.response.LoyaltyCardResponse;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.LoyaltyCard;
import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    /**
     * Возвращает список всех зарегестрированных аккаунтов
     */
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::mapToAccountResponse).toList();
    }

    /**
     * Возвращает зарегистрированный аккаунт по ID
     */
    public AccountResponse getAccountById(UUID accountId) {
        return mapToAccountResponse(accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Возвращает зарегистрированный аккаунт по номеру телефона
     */
    public AccountResponse getAccountByPhone(String phone) {
        return mapToAccountResponse(accountRepository.findAccountByPhone(phone).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Удаляет зарегистрированный аккаунт из БД по ID
     */
    public ResponseEntity<String> removeAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
        return ResponseEntity.ok().body("Аккаунт с ID: " + accountId + " успешно удален!");
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
            .name(account.getName())
            .surname(account.getSurname())
            .email(account.getEmail())
            .phone(account.getPhone())
            .loyaltyCard(mapToLoyaltyCard(account.getLoyaltyCard()))
            .build();
    }

    private LoyaltyCardResponse mapToLoyaltyCard(LoyaltyCard loyaltyCard) {
        if (loyaltyCard == null) return null;
        return LoyaltyCardResponse.builder()
            .cardholder(mapToAccountResponse(loyaltyCard.getAccount()))
            .bonuses(loyaltyCard.getBonuses())
            .accumulatedAmount(loyaltyCard.getAccumulatedAmount())
            .loyaltyLevel(loyaltyCard.getLevel())
            .build();
    }
}
