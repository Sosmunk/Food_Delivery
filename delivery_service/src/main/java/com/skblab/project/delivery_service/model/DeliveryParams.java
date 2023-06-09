package com.skblab.project.delivery_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("DeliveryParams")
public class DeliveryParams implements Serializable {
    @Id
    @Indexed
    private UUID orderId;

    @Indexed
    private String customerPhone;

    @Indexed
    private Address address;

    @Indexed
    private int taken;
}
