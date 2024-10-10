package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.response.CartResponseDTO;

import java.math.BigDecimal;

public interface CartService {
    CartResponseDTO getCartById(Long id);
    void clearCartById(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    CartResponseDTO getCartByUserId(Long userId);
}
