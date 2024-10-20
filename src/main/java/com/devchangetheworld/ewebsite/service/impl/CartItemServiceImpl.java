package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.entities.Cart;
import com.devchangetheworld.ewebsite.entities.CartItem;
import com.devchangetheworld.ewebsite.entities.Product;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.repository.CartItemRepository;
import com.devchangetheworld.ewebsite.repository.CartRepository;
import com.devchangetheworld.ewebsite.repository.ProductRepository;
import com.devchangetheworld.ewebsite.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    public void addCartItemToCart(Long cartId, Long productId, int quality) {
        // Check existed of cart and product
        // If yes, then check if product is in cart or not
        // If in cart, then update quality
        // If not in cart, then add it to cart
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "cartId", cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseGet(CartItem::new);
        //If not exsited then it not have id, as ID is generated when save to db
        if(cartItem.getId() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setQuality(quality);
        }
        else{
            cartItem.setQuality(quality);
        }
        cartItem.setTotalPrice();
        cart.addCartItem(cartItem); //We just add because Set know it the same (equal) so auto replace the old
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeCartItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "cartId", cartId));
        CartItem cartItem = getCartItem(cartId, productId);
        cart.removeCartItem(cartItem);
        cartRepository.save(cart); // Cascade.ALL so it will cascade it item and delete it

    }

    @Override
    public void updateCartItemQuality(Long cartId, Long productId, int quality) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "cartId", cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));

        cartItem.setQuality(quality);
        cartItem.setUnitPrice(product.getPrice());
        cartItem.setTotalPrice();

        cart.updateTotalAmount();

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}
