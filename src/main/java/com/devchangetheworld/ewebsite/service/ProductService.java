package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.request.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.request.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ProductResponseDTO;
import com.devchangetheworld.ewebsite.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.List;


public interface ProductService {
    ProductResponseDTO createProduct(AddProductRequestDTO productRequestDTO);

    ProductResponseDTO updateProduct(Long id, UpdateProductRequestDTO updateProductRequestDTO);

    void deleteProduct(Long id);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProduct();

    Page<ProductResponseDTO> getAllProductPageable(Pageable pageable);
}
