package com.devchangetheworld.ewebsite.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemResponseDTO {

    private Long id;
    private int quality;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductResponseDTO product;
    private Long cartId;
}
