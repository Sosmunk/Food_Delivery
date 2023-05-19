package com.skblab.project.delivery_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
public class OrderInDeliveryEvent {
    UUID orderId;
    UUID courierId;
    String name;
    String phone;
    Timestamp deliveryStartTimestamp;
}
