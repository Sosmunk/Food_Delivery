package com.example.order_service.repository;

import com.example.order_service.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    MenuItem findMenuItemByName(String name);
}
