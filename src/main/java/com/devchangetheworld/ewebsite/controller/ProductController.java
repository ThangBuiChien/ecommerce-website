package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.product.ProductSearchCriteria;
import com.devchangetheworld.ewebsite.dto.product.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.product.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.dto.product.ProductResponseDTO;
import com.devchangetheworld.ewebsite.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Product got successfully")
                .result(productService.getProductById(id))
                .build());
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(ApiResponse.builder()
                        .message("Product created successfully")
                        .result(productService.createProduct(productRequestDTO))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id,
                                                     @RequestBody UpdateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Product updated successfully")
                .result(productService.updateProduct(id, productRequestDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  ResponseEntity.ok(ApiResponse.builder()
                        .message("Product deleted successfully")
                .build());
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllProduct(Pageable pageable){
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Product got successfully")
                .result(productService.getAllProductPageable(pageable))
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchProducts(
            ProductSearchCriteria criteria,
            Pageable pageable) {

        Page<ProductResponseDTO> products = productService.searchProducts(criteria, pageable);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Search/filter product successfully")
                .result(products)
                .build());
    }

}
