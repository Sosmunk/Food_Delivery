package com.skblab.project.delivery_service.repository;

import com.skblab.project.delivery_service.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.Optional;
import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    Optional<Courier> findFirstByIsAvailable(Boolean isAvailable);
    Optional<Courier> findCourierByOrderId(UUID orderId);

    @Modifying
    @Query(value = "UPDATE Courier c SET c.isAvailable = ?1, c.orderId = ?2 WHERE c.courierId = ?3")
    void changeCourierAvailability(@Param("is_available") boolean isAvailable,
                                   @Param("order_id") UUID orderId,
                                   @Param("courier_id") UUID courierId);
}
