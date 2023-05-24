package com.skblab.project.delivery_service.service;

import com.skblab.project.delivery_service.event.CourierFreedEvent;
import com.skblab.project.delivery_service.model.Courier;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierEventPub {

    private final ApplicationEventPublisher eventPublisher;

    void publishCourierIsFreed(Courier courier) {
        eventPublisher.publishEvent(new CourierFreedEvent(this, courier));
    }

}
