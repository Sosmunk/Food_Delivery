package com.example.order_service.web.controller;

import com.example.order_service.common.annotation.LogMethodExecution;
import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.metrics.OrderMetric;
import com.example.order_service.service.JWTService;
import com.example.order_service.service.impl.OrderServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/order")
@Slf4j
public class OrderController {
    OrderServiceImpl orderService;

    OrderMetric orderMetric;

    JWTService jwtService;

    @PostMapping("/post")
    @LogMethodExecution
    @Transactional
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String token) {
        Claims claims = jwtService.extractAllClaims(jwtService.extractJwtToken(token));
        OrderResponse response = orderService.placeOrder(orderRequest, claims);
        orderMetric.incrementOrderCounter();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/orders")
    public ResponseEntity<List<OrderResponse>> getAccountOrders(@RequestHeader("Authorization") String token) {
        Claims claims = jwtService.extractAllClaims(jwtService.extractJwtToken(token));
        return ResponseEntity.ok(orderService.getAccountOrders(UUID.fromString(claims.get("accountId", String.class))));
    }

}
