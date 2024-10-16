package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.order.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Order get by id successfully")
                .result(orderService.getOrderById(id))
                .build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addOrder(@PathVariable Long id, @RequestBody Set<AddOrderItemRequestDTO> orderItemRequestDTO){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Order created successfully")
                .result(orderService.placeOrder(id, orderItemRequestDTO))
                .build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getAllOrdersByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Order get by user's id successfully")
                .result(orderService.getAllOrdersByUser(userId))
                .build());
    }
}
