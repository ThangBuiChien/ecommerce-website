package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.CartItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
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
