package com.skblab.project.account_service.repository;

import com.skblab.project.account_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * <b>Репозиторий аккаунтов</b>
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByPhone(String phone);
    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findAccountByName(String name);
}
