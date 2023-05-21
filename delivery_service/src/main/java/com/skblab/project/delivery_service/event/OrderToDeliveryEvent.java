package com.skblab.project.delivery_service.event;

import com.skblab.project.delivery_service.model.Address;
import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
public class OrderToDeliveryEvent {
    UUID orderId;
    String phone;
    Address address;
}
