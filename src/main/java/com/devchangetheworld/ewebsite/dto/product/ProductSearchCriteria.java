package com.devchangetheworld.ewebsite.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSearchCriteria {
    private String name;
    private String brand;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long categoryId;
    private Boolean inStock;
}
