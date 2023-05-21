package com.skblab.project.account_service.service;

import com.skblab.project.account_service.dto.response.AccountResponse;
import com.skblab.project.account_service.dto.response.LoyaltyCardResponse;
import com.skblab.project.account_service.dto.amqp.OrderPaidEvent;
import com.skblab.project.account_service.model.Account;
import com.skblab.project.account_service.model.LoyaltyCard;
import com.skblab.project.account_service.model.LoyaltyLevel;
import com.skblab.project.account_service.repository.AccountRepository;
import com.skblab.project.account_service.repository.LoyaltyCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class LoyaltyCardService {
    private final LoyaltyCardRepository loyaltyCardRepository;
    private final AccountRepository accountRepository;

    /**
     * Возвращает карту лояльности по ID аккаунта, к которому она привязана
     */
    public LoyaltyCardResponse getLoyaltyCardByAccountId(UUID accountId) {
        return mapToLoyaltyCard(loyaltyCardRepository.findLoyaltyCardByAccount_AccountId(accountId));
    }

    /**
     * Создает новую карту лояльности по ID аккаунта и записывает в БД
     */
    public ResponseEntity<String> createLoyaltyCardByAccountId(UUID accountId) {
        if (accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new).getLoyaltyCard() == null) {
            LoyaltyCard loyaltyCard = LoyaltyCard.builder()
                .account(accountRepository.findById(accountId).orElseThrow(EntityNotFoundException::new))
                .bonuses(0)
                .accumulatedAmount(0.0)
                .level(LoyaltyLevel.COMMON)
                .build();
            loyaltyCardRepository.save(loyaltyCard);
            return ResponseEntity.created(URI.create("/api/loyalty_card/" + accountId))
                .body("Карта лояльности для аккаунта с ID: " + accountId + " успешно создана");
        } else {
            return ResponseEntity.badRequest().body("Аккаунт не может быть связан более чем с одной картой лояльности\n" +
                "При необходимости, измените или удалите существующую!");
        }
    }

    /**
     * Удаляет карту лояльности по ID аккаунта, к которой она привязана
     */
    public ResponseEntity<String> removeLoyaltyCardByAccountId(UUID accountId) {
        if (loyaltyCardRepository.findById(accountId).isEmpty()) {
            return ResponseEntity.badRequest().body("Операция не может быть выполнена! \n" +
                    "Карта лояльности для аккаунта с ID: " + accountId + " не зарегистрирована!");
        } else {
            loyaltyCardRepository.deleteLoyaltyCardByAccount_AccountId(accountId);
            return ResponseEntity.ok().body("Карта лояльности для аккаунта с ID: " + accountId + " успешно удалена!");
        }
    }

    /**
     * Накапливает бонусы на карте в соответствии с текущим уровнем лояльности
     */
    public void accumulateBonuses(OrderPaidEvent event) {
        LoyaltyCard loyaltyCard = loyaltyCardRepository.findById(event.getAccountId())
                .orElseThrow(EntityNotFoundException::new);
        if (event.getSpentBonuses() > loyaltyCard.getBonuses()) {
            log.error("Бонусов на карте меньше, чем использовано в платеже");
        } else {
            loyaltyCard.setAccumulatedAmount(loyaltyCard.getAccumulatedAmount() + event.getOrderPrice());
            loyaltyCard.setBonuses((int)(loyaltyCard.getBonuses() +
                (event.getSpentBonuses() * loyaltyCard.getLevel().getBonusesPercent() * 0.01))
                - event.getSpentBonuses());
            loyaltyCard.setLevel(getLoyaltyLevelByAccumulatedAmount(loyaltyCard.getAccumulatedAmount()));
        }
    }

    /**
     * Возвращает уровень лояльности по накопленной сумме заказов
     */
    private LoyaltyLevel getLoyaltyLevelByAccumulatedAmount(Double accumulatedAmount) {
        LoyaltyLevel currentLevel = LoyaltyLevel.COMMON;
        for (LoyaltyLevel level : LoyaltyLevel.values()) {
            if (accumulatedAmount >= level.getRequiredOrdersSum()) {
                currentLevel = level;
            }
        }
        return currentLevel;
    }

    private LoyaltyCardResponse mapToLoyaltyCard(LoyaltyCard loyaltyCard) {
        return LoyaltyCardResponse.builder()
                .cardholder(mapToAccountResponse(loyaltyCard.getAccount()))
                .bonuses(loyaltyCard.getBonuses())
                .accumulatedAmount(loyaltyCard.getAccumulatedAmount())
                .loyaltyLevel(loyaltyCard.getLevel())
                .build();
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .name(account.getName())
                .email(account.getEmail())
                .phone(account.getPhone())
                .build();
    }
}
