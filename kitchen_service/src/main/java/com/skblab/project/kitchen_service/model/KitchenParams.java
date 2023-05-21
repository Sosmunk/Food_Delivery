package com.skblab.project.kitchen_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("KitchenParams")
public class KitchenParams implements Serializable {
    @Id
    @Indexed
    private UUID orderId;
    private List<OrderMenuItem> orderMenuItems;
}
