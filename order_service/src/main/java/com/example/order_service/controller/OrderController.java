package com.example.order_service.controller;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.repository.MenuItemRepository;
import com.example.order_service.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/order")
public class OrderController {
    OrderService orderService;
    MenuItemRepository menuItemRepository;
    @PostMapping("/post")
    public ResponseEntity<String> postOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.OK);
    }
    @GetMapping("/cabbage")
    public String getCabbage() {
        return menuItemRepository.findMenuItemByName("cabbage").toString();
    }
}
