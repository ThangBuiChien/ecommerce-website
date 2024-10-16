package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.product.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.product.ProductResponseDTO;
import com.devchangetheworld.ewebsite.dto.product.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.entities.Category;
import com.devchangetheworld.ewebsite.entities.Product;
import com.devchangetheworld.ewebsite.mapper.AutoProductMapper;
import com.devchangetheworld.ewebsite.repository.CategoryRepository;
import com.devchangetheworld.ewebsite.repository.ProductRepository;
import com.devchangetheworld.ewebsite.specification.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Test github in action

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.show-sql=true"
})
public class ProductServiceImplIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AutoProductMapper autoProductMapper;

    @Autowired
    private ProductSpecification productSpecification;

    private Category category;

    private Product product;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        category = new Category();
        category.setName("Original Category");
        categoryRepository.save(category);

        // Create and save the product with the created category
        product = new Product();
        product.setName("Original Product");
        product.setBrand("Original Brand");
        product.setDescription("Original Description");
        product.setPrice(new BigDecimal("50.00"));
        product.setInventory(50);
        product.setCategory(category);
        product = productRepository.save(product);

    }

    @Test
    void testCreateProduct() {
        AddProductRequestDTO requestDTO = new AddProductRequestDTO();
        requestDTO.setName("Test Product");
        requestDTO.setBrand("Test Brand");
        requestDTO.setDescription("Test Description");
        requestDTO.setPrice(new BigDecimal("99.99"));
        requestDTO.setInventory(100);
        requestDTO.setCategoryName("Test Category");

        ProductResponseDTO responseDTO = productService.createProduct(requestDTO);

//        assertNotNull("haha");

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getId());
        assertEquals(requestDTO.getName(), responseDTO.getName());
        assertEquals(requestDTO.getBrand(), responseDTO.getBrand());
        assertEquals(requestDTO.getDescription(), responseDTO.getDescription());
        assertEquals(requestDTO.getPrice(), responseDTO.getPrice());
        assertEquals(requestDTO.getInventory(), responseDTO.getInventory());
        assertEquals(requestDTO.getCategoryName(), responseDTO.getCategoryName());
        assertTrue(responseDTO.getImages().isEmpty());
    }

    @Transactional
    @Test
    void testUpdateProduct() {

        UpdateProductRequestDTO updateDTO = new UpdateProductRequestDTO();
        updateDTO.setName("Updated Product");
        updateDTO.setBrand("Updated Brand");
        updateDTO.setDescription("Updated Description");
        updateDTO.setPrice(new BigDecimal("75.00"));
        updateDTO.setInventory(75);
        updateDTO.setCategoryName("Updated Category");
        updateDTO.setImages(new ArrayList<>()); // Assuming Image is a valid entity

        ProductResponseDTO updatedProduct = productService.updateProduct(product.getId(), updateDTO);

        assertNotNull(updatedProduct);
        assertEquals(product.getId(), updatedProduct.getId());
        assertEquals(updateDTO.getName(), updatedProduct.getName());
        assertEquals(updateDTO.getBrand(), updatedProduct.getBrand());
        assertEquals(updateDTO.getDescription(), updatedProduct.getDescription());
        assertEquals(updateDTO.getPrice(), updatedProduct.getPrice());
        assertEquals(updateDTO.getInventory(), updatedProduct.getInventory());
        assertEquals(updateDTO.getCategoryName(), updatedProduct.getCategoryName());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(product.getId());

        assertFalse(productRepository.existsById(product.getId()));
    }

    @Transactional
    @Test
    void testGetProductById() {

        ProductResponseDTO foundProduct = productService.getProductById(product.getId());

        assertNotNull(foundProduct);
        assertEquals(product.getId(), foundProduct.getId());
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getBrand(), foundProduct.getBrand());
        assertEquals(product.getDescription(), foundProduct.getDescription());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getInventory(), foundProduct.getInventory());
        assertEquals(product.getCategory().getName(), foundProduct.getCategoryName());
    }
}
