package com.skblab.project.kitchen_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuItem implements Serializable {
    Long itemId;
    String name;
    Integer quantity;
}