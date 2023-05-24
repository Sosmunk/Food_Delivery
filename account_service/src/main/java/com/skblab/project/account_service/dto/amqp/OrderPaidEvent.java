package com.skblab.project.account_service.dto.amqp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * <b>DTO оплаченного заказа из очереди RabbitMQ</b><br>
 * Список полей:<br>
 * {@link OrderPaidEvent#accountId} – <i>UUID аккаунта</i><br>
 * {@link OrderPaidEvent#orderPrice} – <i>Сумма заказа</i><br>
 * {@link OrderPaidEvent#spentBonuses} – <i>Потраченные бонусы</i>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaidEvent {
    UUID accountId;
    Double orderPrice;
    Integer spentBonuses;
}