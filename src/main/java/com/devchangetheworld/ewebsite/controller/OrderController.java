package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.request.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ApiResponse;
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

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> addCart(@PathVariable Long id, @RequestBody Set<AddOrderItemRequestDTO> orderItemRequestDTO){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Order created successfully")
                .result(orderService.placeOrder(id, orderItemRequestDTO))
                .build());
    }

}
