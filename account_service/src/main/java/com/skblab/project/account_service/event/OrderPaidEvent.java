package com.skblab.project.account_service.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
@Getter
@Setter
public class OrderPaidEvent {
    UUID accountId;
    Double orderPrice;
    Integer spentBonuses;
}