package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.product.ProductSearchCriteria;
import com.devchangetheworld.ewebsite.dto.product.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.product.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.product.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    ProductResponseDTO createProduct(AddProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, UpdateProductRequestDTO updateProductRequestDTO);

    void deleteProduct(Long id);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProduct();

    Page<ProductResponseDTO> getAllProductPageable(Pageable pageable);

    Page<ProductResponseDTO> searchProducts(ProductSearchCriteria criteria, Pageable pageable);
}
