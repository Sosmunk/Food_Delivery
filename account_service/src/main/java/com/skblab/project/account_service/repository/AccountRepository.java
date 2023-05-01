package com.skblab.project.account_service.repository;

import com.skblab.project.account_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
