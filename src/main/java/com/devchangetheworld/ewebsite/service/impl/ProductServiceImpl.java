package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.ProductSearchCriteria;
import com.devchangetheworld.ewebsite.dto.request.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.request.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ProductResponseDTO;
import com.devchangetheworld.ewebsite.entities.Product;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoProductMapper;
import com.devchangetheworld.ewebsite.repository.ProductRepository;
import com.devchangetheworld.ewebsite.service.ProductService;
import com.devchangetheworld.ewebsite.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final AutoProductMapper autoProductMapper;

    private final ProductSpecification productSpecification;
    @Override
    public ProductResponseDTO createProduct(AddProductRequestDTO productRequestDTO) {
        Product product = autoProductMapper.toEntity(productRequestDTO);

        return autoProductMapper.toResponseDTO(productRepository.save(product));

    }

    @Override
    public ProductResponseDTO updateProduct(Long id, UpdateProductRequestDTO updateProductRequestDTO) {
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        Product updatedProduct = autoProductMapper.toEntity(updateProductRequestDTO);
//        product.getImages().clear();
        updatedProduct.setId(id);
        updatedProduct.setImages(product.getImages());

        return autoProductMapper.toResponseDTO(productRepository.save(updatedProduct));

    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(product -> productRepository.deleteById(id),
                        () -> {throw new ResourceNotFoundException("product", "id", id);});
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(autoProductMapper :: toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
    }

    @Override
    public List<ProductResponseDTO> getAllProduct() {

        return productRepository.findAll().stream()
                .map(autoProductMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Page<ProductResponseDTO> getAllProductPageable(Pageable pageable) {
        return productRepository.findAll(pageable).map(autoProductMapper::toResponseDTO);
    }

    @Override
    public Page<ProductResponseDTO> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = productSpecification.getSearchSpecification(criteria);
        return productRepository.findAll(spec, pageable).map(autoProductMapper::toResponseDTO);
    }
}
