package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.cart.CartResponseDTO;
import com.devchangetheworld.ewebsite.entities.Cart;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoCartMapper;
import com.devchangetheworld.ewebsite.repository.CartRepository;
import com.devchangetheworld.ewebsite.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final AutoCartMapper autoCartMapper;

    @Override
    public CartResponseDTO getCartById(Long id) {
        return cartRepository.findById(id).map(autoCartMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "id", id));
    }

    @Override
    public void clearCartById(Long id) {
        //with castCade.ALL we don't need to remove cartItem manually
        cartRepository.findById(id)
                .ifPresentOrElse(cartRepository::delete,
                        () -> {throw new ResourceNotFoundException("cart", "id", id);});
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "id", id))
                .getTotalAmount();

    }

    @Override
    public Long initializeNewCart() {
        return cartRepository.save(new Cart()).getId();
    }

    @Override
    public CartResponseDTO getCartByUserId(Long userId) {
        return null;
    }
}
