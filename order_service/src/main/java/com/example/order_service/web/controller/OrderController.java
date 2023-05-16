package com.example.order_service.web.controller;

import com.example.order_service.domain.dto.request.OrderRequest;
import com.example.order_service.domain.dto.response.OrderResponse;
import com.example.order_service.metrics.OrderMetric;
import com.example.order_service.repository.MenuItemRepository;
import com.example.order_service.service.impl.OrderServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/post")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest orderRequest) {
        log.info("Поступил запрос на заказ");
        OrderResponse response = orderService.placeOrder(orderRequest);
        orderMetric.incrementOrderCounter();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/cabbage")
    public String getCabbage() {
        return menuItemRepository.findMenuItemByName("cabbage").toString();
    }
}
