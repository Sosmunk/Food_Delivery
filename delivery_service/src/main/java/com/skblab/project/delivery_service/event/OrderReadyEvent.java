package com.skblab.project.delivery_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OrderReadyEvent {
    Long orderId;
}
