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
    private final CourierEventPub courierEventPub;

    public void changeOrderStatusToInDelivery(UUID orderId, Courier courier) {
        OrderInDeliveryEvent event = OrderInDeliveryEvent.builder()
            .orderId(orderId)
            .name(courier.getName())
            .phone(courier.getPhone())
            .deliveryStartTimestamp(new Timestamp(System.currentTimeMillis()))
            .build();
        deliveryEventPub.changeOrderStatusToInDelivery(event);
        courierRepository.changeCourierAvailability(false, orderId, courier.getCourierId());
        getDeliveryParams(orderId).setTaken(true);
    }

    public void changeOrderStatusToDelivered(UUID orderId) {
        Courier courier = courierService.getCourierByOrderId(orderId);
        OrderDeliveredEvent deliveredEvent = OrderDeliveredEvent.builder()
            .orderId(orderId)
            .deliveryEndTimestamp(new Timestamp(System.currentTimeMillis()))
            .build();
        deliveryEventPub.changeOrderStatusToDelivered(deliveredEvent);
        courierEventPub.publishCourierIsFreed(courier);
        courierRepository.changeCourierAvailability(true, null, courier.getCourierId());
        removeDeliveryParams(orderId);
    }

    public DeliveryParams getDeliveryParams(UUID orderId) {
        return deliveryParamsRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    public List<DeliveryParams> getNotTakenDeliveryParams() {
        return deliveryParamsRepository.findAllByTaken(false);
    }

    public List<DeliveryParams> getTakenDeliveryParams() {
        return deliveryParamsRepository.findAllByTaken(true);
    }

    public void createDeliveryParams(OrderToDeliveryEvent event) {
        Address address = event.getAddress();
        DeliveryParams deliveryParams = DeliveryParams.builder()
            .orderId(event.getOrderId())
            .customerPhone(event.getPhone())
            .address(address)
            .isTaken(false)
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
