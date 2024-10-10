package com.devchangetheworld.ewebsite.dto.request;

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
public class AddOrderItemRequestDTO {
    private int quality;
    private Long productId;
}
