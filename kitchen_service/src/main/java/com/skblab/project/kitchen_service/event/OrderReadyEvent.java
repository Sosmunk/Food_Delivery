package com.skblab.project.kitchen_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
@Getter
@Setter
public class OrderReadyEvent {
    UUID orderId;
}
