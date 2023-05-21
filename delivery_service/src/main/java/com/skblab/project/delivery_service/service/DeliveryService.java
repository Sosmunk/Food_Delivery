package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.event.OrderDeliveredEvent;
import com.skblab.project.delivery_service.event.OrderInDeliveryEvent;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class DeliveryService {
    private final CourierRepository courierRepository;
    private final DeliveryEventPub deliveryEventPub;
    private final CourierService courierService;

    public void changeOrderStatusToInDelivery(UUID orderId) {
        Courier choosenCourier = courierService.getAvailableCourier();

        if (choosenCourier != null) {
            OrderInDeliveryEvent event = OrderInDeliveryEvent.builder()
                    .orderId(orderId)
                    .name(choosenCourier.getName())
                    .phone(choosenCourier.getPhone())
                    .deliveryStartTimestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            deliveryEventPub.changeOrderStatusToInDelivery(event);
            courierRepository.changeCourierAvailability(false, orderId, choosenCourier.getCourierId());
        } else {
            log.info("Заказ с ID " + orderId + " не может быть принят в данный момент. Нет свободных курьеров");
        }
    }

    public void changeOrderStatusToDelivered(UUID orderId) {
        Courier courier = courierService.getCourierByOrderId(orderId);
        OrderDeliveredEvent deliveredEvent = OrderDeliveredEvent.builder()
                .orderId(orderId)
                .deliveryEndTimestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        deliveryEventPub.changeOrderStatusToDelivered(deliveredEvent);
        courierRepository.changeCourierAvailability(true, null, courier.getCourierId());
    }
}
