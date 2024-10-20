package com.devchangetheworld.ewebsite.dto.product;

import com.devchangetheworld.ewebsite.entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddProductRequestDTO {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private String categoryName;
}
