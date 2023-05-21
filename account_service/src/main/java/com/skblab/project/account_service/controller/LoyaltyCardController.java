package com.skblab.project.account_service.controller;

import com.skblab.project.account_service.dto.response.LoyaltyCardResponse;
import com.skblab.project.account_service.service.LoyaltyCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * <b>Контроллер для управления картами лояльности пользователей</b>
 */
@RestController
@RequestMapping("/api/loyalty_card")
@RequiredArgsConstructor
public class LoyaltyCardController {
    private final LoyaltyCardService loyaltyCardService;

    @GetMapping("/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LoyaltyCardResponse getLoyaltyCardByAccountId(@PathVariable("account_id") UUID accountId) {
        return loyaltyCardService.getLoyaltyCardByAccountId(accountId);
    }

    @PostMapping("/{account_id}")
    public ResponseEntity<String> createLoyaltyCardByAccountId(@PathVariable("account_id") UUID accountId) {
        return loyaltyCardService.createLoyaltyCardByAccountId(accountId);
    }

    @DeleteMapping("/{account_id}")
    public ResponseEntity<String> removeLoyaltyCardByAccountId(@PathVariable("account_id") UUID accountId) {
        return loyaltyCardService.removeLoyaltyCardByAccountId(accountId);
    }
}
