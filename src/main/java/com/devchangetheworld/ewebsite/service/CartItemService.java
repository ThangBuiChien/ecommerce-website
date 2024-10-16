package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.entities.CartItem;

public interface CartItemService {
    void addCartItemToCart(Long cartId, Long productId, int quality);
    void removeCartItemFromCart(Long cartId, Long productId);
    void updateCartItemQuality(Long cartId, Long productId, int quality);
    CartItem getCartItem(Long cartId, Long productId);
}
