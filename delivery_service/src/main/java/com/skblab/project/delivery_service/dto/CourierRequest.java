package com.skblab.project.delivery_service.dto;

import lombok.*;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourierRequest {
    private String name;
    private String surname;
    private String phone;
}
