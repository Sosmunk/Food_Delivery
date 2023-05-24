package com.skblab.project.delivery_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DeliveryOrderCounterEngine {

    private final DeliveryService deliveryService;

    @Scheduled(fixedRate = 1000 * 60)
    @Async
    public void logOrdersCount() {
        log.info("[x] В данный момент " + deliveryService.getNotTakenDeliveryParams().size() + " заказов находятся в ожидании [x]");
        log.info("[x] В данный момент " + deliveryService.getTakenDeliveryParams().size() + " заказов находятся в доставке [x]");
        log.info("[x] Сейчас всего " + deliveryService.getAllDeliveryParams().size() + " заказов [x]");
    }

}
