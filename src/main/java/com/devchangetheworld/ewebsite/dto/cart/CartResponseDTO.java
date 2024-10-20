package com.devchangetheworld.ewebsite.dto.cart;

import com.devchangetheworld.ewebsite.dto.cart.CartItemResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CartResponseDTO {

    private Long id;
    private BigDecimal totalAmount;
    private Set<CartItemResponseDTO> cartItem;

}
