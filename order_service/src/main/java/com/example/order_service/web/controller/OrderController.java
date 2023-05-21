package com.example.order_service.web.controller;

import com.example.order_service.common.annotation.LogMethodExecution;
import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.metrics.OrderMetric;
import com.example.order_service.repository.MenuItemRepository;
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

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/order")
@Slf4j
public class OrderController {
    OrderServiceImpl orderService;
    MenuItemRepository menuItemRepository;
    OrderMetric orderMetric;

    JWTService jwtService;

    @PostMapping("/post")
    @LogMethodExecution
    @Transactional
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String token) {
        Claims claims = jwtService.extractAllClaims(jwtService.extractJwtToken(token));
        log.info(claims.toString());
        OrderResponse response = orderService.placeOrder(orderRequest);
        orderMetric.incrementOrderCounter();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/cabbage")
    public String getCabbage() {
        return menuItemRepository.findMenuItemByName("cabbage").toString();
    }

}
