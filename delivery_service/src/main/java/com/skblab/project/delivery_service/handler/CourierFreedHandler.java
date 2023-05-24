package com.skblab.project.delivery_service.handler;

import com.skblab.project.delivery_service.event.CourierFreedEvent;
import com.skblab.project.delivery_service.model.DeliveryParams;
import com.skblab.project.delivery_service.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourierFreedHandler {
    private final DeliveryService deliveryService;

    @EventListener
    public void handleCourierFreedEvent(CourierFreedEvent event) {
        List<DeliveryParams> deliveryOrders = deliveryService.getNotTakenDeliveryParams();
        DeliveryParams chosenOrder;
        if (!deliveryOrders.isEmpty()) {
            chosenOrder = deliveryOrders.get(0);
            deliveryService.changeOrderStatusToInDelivery(chosenOrder.getOrderId(), event.getCourier());
        }
    }
}
