package com.skblab.project.account_service.event;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class OrderPaidEvent {
    UUID accountId;
    Double orderPrice;
    Integer spentBonuses;
}