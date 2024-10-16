package com.devchangetheworld.ewebsite.dto.order;

import com.devchangetheworld.ewebsite.dto.product.ProductResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDTO {
    private Long id;
    private int quality;
    private BigDecimal unitPrice;
    private ProductResponseDTO product;

//    private Order order;
}
