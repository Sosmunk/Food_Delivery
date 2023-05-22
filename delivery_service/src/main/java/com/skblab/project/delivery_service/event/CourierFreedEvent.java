package com.skblab.project.delivery_service.event;

import com.skblab.project.delivery_service.model.Courier;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CourierFreedEvent extends ApplicationEvent {
    private Courier courier;

    public CourierFreedEvent(Object source, Courier courier) {
        super(source);
        this.courier = courier;
    }
}
