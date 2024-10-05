package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.Category;
import com.devchangetheworld.ewebsite.entities.Image;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private String categoryName;
    private List<Image> images;
}
