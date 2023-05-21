package com.skblab.project.account_service.controller;

import com.skblab.project.account_service.dto.response.AccountResponse;
import com.skblab.project.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * <b>Контроллер для получения, изменения и удаления аккаунтов</b>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/id/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AccountResponse getAccountById(@PathVariable("account_id") UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("/phone/{account_phone}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AccountResponse getAccountByPhone(@PathVariable("account_phone") String phone) {
        return accountService.getAccountByPhone(phone);
    }

    @DeleteMapping("/{account_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> removeAccountById(@PathVariable("account_id") UUID accountId) {
        return accountService.removeAccount(accountId);
    }
}
