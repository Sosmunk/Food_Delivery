package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.repository.MenuItemRepository;
import com.example.order_service.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;
    MenuItemRepository menuItemRepository;
    @RequestMapping("/order")
    public ResponseEntity<String> postOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.OK);
    }
    @GetMapping("/cabbage")
    public String getCabbage() {
        return menuItemRepository.findMenuItemByName("cabbage").toString();
    }
}
