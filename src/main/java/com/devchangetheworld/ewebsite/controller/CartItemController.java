package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.cart_item.AddCartItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.cart_item.RemoveCartItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.cart_item.UpdateCartItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddCartItemRequestDTO cartItemRequestDTO) {
        cartItemService.addCartItemToCart(cartItemRequestDTO.getCartId(), cartItemRequestDTO.getProductId(),
                cartItemRequestDTO.getQuality());
        return ResponseEntity.ok(ApiResponse.builder()
            .message("Add cart item to cart successfully")
            .build());
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateQuality(@RequestBody UpdateCartItemRequestDTO updateCartItemRequestDTO) {
        cartItemService.updateCartItemQuality(updateCartItemRequestDTO.getCartId(), updateCartItemRequestDTO.getProductId(),
                updateCartItemRequestDTO.getQuality());
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Update item's quality successfully")
                .build());
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestBody RemoveCartItemRequestDTO removeCartItemRequestDTO) {
        cartItemService.removeCartItemFromCart(removeCartItemRequestDTO.getCartId(),
                removeCartItemRequestDTO.getProductId());
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Remove item from cart successfully")
                .build());
    }


}
