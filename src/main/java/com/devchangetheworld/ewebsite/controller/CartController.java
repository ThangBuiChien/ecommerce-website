package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Get cart by ID successfully")
                .result(cartService.getCartById(id))
                .build());
    }


    @PostMapping("")
    public ResponseEntity<ApiResponse> addCart(){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Cart created successfully")
                .result(cartService.initializeNewCart())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id){
        cartService.clearCartById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Clear cart successfully")
                .build());
    }

}
