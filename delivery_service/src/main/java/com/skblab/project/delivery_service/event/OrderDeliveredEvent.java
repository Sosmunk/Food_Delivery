package com.skblab.project.delivery_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Builder
@Getter
@Setter
public class OrderDeliveredEvent {
    Long orderId;
    Timestamp deliveryEndTimestamp;
}
