package com.skblab.project.delivery_service.dto;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourierResponse {
    private String name;
    private String phone;
}
