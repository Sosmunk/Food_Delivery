package com.skblab.project.delivery_service.repository;

import com.skblab.project.delivery_service.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Courier findFirstByIsAvailable(Boolean isAvailable);
    Courier findCourierByOrderId(UUID orderId);
}
