package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.Order;
import com.devchangetheworld.ewebsite.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
