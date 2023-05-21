package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.event.OrderDeliveredEvent;
import com.skblab.project.delivery_service.event.OrderInDeliveryEvent;
import com.skblab.project.delivery_service.event.OrderToDeliveryEvent;
import com.skblab.project.delivery_service.model.Address;
import com.skblab.project.delivery_service.model.Courier;
import com.skblab.project.delivery_service.model.DeliveryParams;
import com.skblab.project.delivery_service.repository.CourierRepository;
import com.skblab.project.delivery_service.repository.DeliveryParamsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class DeliveryService {
    private final DeliveryParamsRepository deliveryParamsRepository;
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
        removeDeliveryParams(orderId);
    }

    public DeliveryParams getDeliveryParams(UUID orderId) {
        return deliveryParamsRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    public void createDeliveryParams(OrderToDeliveryEvent event) {
        Address address = event.getAddress();
        DeliveryParams deliveryParams = DeliveryParams.builder()
            .orderId(event.getOrderId())
            .customerPhone(event.getPhone())
            .address(address)
            .build();
        deliveryParamsRepository.save(deliveryParams);
    }

    public void removeDeliveryParams(UUID orderId) {
        deliveryParamsRepository.deleteById(orderId);
    }

    public List<DeliveryParams> getAllDeliveryParams() {
        List<DeliveryParams> result = new ArrayList<>();
        deliveryParamsRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }
}
