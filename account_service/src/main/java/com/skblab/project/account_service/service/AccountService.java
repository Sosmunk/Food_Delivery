package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.AccountRequest;
import com.skblab.project.account_service.dto.AccountResponse;
import com.skblab.project.account_service.dto.LoyaltyCardResponse;
import com.skblab.project.account_service.exception.EmailExistsException;
import com.skblab.project.account_service.exception.PhoneExistsException;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.LoyaltyCard;
import com.skblab.project.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

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
        return mapToAccountResponse(accountRepository.findAccountByPhone(phone));
    }

    /**
     * Создает новый аккаунт и записывает в БД
     */
    public ResponseEntity<String> createAccount(AccountRequest request) throws PhoneExistsException, EmailExistsException {
        if (phoneExists(request.getPhone())) {
            throw new PhoneExistsException("Телефон " + request.getPhone() + " уже привязан к другому аккаунту!");
        }
        if ((request.getEmail().isBlank() || request.getEmail().isEmpty()) && emailExists(request.getEmail())) {
            throw new EmailExistsException("Email: " + request.getEmail() + " уже привязан к другому аккаунту!");
        }
        Account account = Account.builder()
            .name(request.getName())
            .surname(request.getSurname())
//            .password(passwordEncoder.encode(request.getPassword()))
            .password(request.getPassword())
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

    private boolean phoneExists(String phone) {
        return accountRepository.findAccountByPhone(phone) != null;
    }

    private boolean emailExists(String email) {
        return accountRepository.findAccountByEmail(email) != null;
    }
}
