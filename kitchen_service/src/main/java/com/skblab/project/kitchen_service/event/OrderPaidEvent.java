package com.skblab.project.kitchen_service.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skblab.project.kitchen_service.model.KitchenParams;
import com.skblab.project.kitchen_service.model.OrderMenuItem;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPaidEvent {
    @JsonProperty(value = "orderId")
    UUID orderId;
    @JsonProperty(value = "orderMenuItems")
    List<OrderMenuItem> orderMenuItems;
}
