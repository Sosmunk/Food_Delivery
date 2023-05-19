package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.event.OrderDeliveredEvent;
import com.skblab.project.delivery_service.event.OrderInDeliveryEvent;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private CourierRepository courierRepository;
    private final DeliveryEventPub deliveryEventPub;
    private final CourierService courierService;

    public void changeOrderStatusToInDelivery(UUID orderId) {
        Courier choosenCourier = courierService.getAvailableCourier();
        OrderInDeliveryEvent event = OrderInDeliveryEvent.builder()
            .orderId(orderId)
            .name(choosenCourier.getName())
            .phone(choosenCourier.getPhone())
            .deliveryStartTimestamp(new Timestamp(System.currentTimeMillis()))
            .build();
        deliveryEventPub.changeOrderStatusToInDelivery(event);
        choosenCourier.setIsAvailable(false);
    }

    public void changeOrderStatusToDelivered(UUID orderId) {
        OrderDeliveredEvent deliveredEvent = OrderDeliveredEvent.builder()
                .orderId(orderId)
                .deliveryEndTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        deliveryEventPub.changeOrderStatusToDelivered(deliveredEvent);
        courierRepository.findCourierByOrderId(orderId).setIsAvailable(true);
    }
}
