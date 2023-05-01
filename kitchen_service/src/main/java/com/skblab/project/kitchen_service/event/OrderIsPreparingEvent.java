package com.skblab.project.kitchen_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class OrderIsPreparingEvent {
    Long orderId;
}
