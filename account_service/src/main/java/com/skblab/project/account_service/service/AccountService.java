package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.AccountRequest;
import com.skblab.project.account_service.dto.AccountResponse;
import com.skblab.project.account_service.dto.LoyaltyCardResponse;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.LoyaltyCard;
import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
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
     * Создает новый аккаунт и записывает в БД
     */
    public ResponseEntity<String> createAccount(AccountRequest request) {
        Account account = Account.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .email(request.getEmail())
            .phone(request.getPhone())
            .build();
        accountRepository.save(account);
        return ResponseEntity.created(URI.create("/api/account/" + account.getAccountId()))
            .body("Аккаунт с ID: " + account.getAccountId() + " успешно создан!");
    }

    /**
     * Удаляет зарегистрированный аккаунт из БД по ID
     */
    public ResponseEntity<String> removeAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
        return ResponseEntity.ok().body("Аккаунт с ID: " + accountId + " успешно удален!");
    }

    /**
     * Удаляет все зарегистрированные аккаунты из БД
     */
    public ResponseEntity<String> removeAllAccounts() {
        accountRepository.findAll();
        return ResponseEntity.ok().body("Все аккаунты успешно удалены!");
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
