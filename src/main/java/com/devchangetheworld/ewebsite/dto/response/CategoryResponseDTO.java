package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private List<ProductResponseDTO> products;
}
