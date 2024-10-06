package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.response.CategoryResponseDTO;
import com.devchangetheworld.ewebsite.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategory();
    Page<CategoryResponseDTO> getAllCategoryPageable(Pageable pageable);
    CategoryResponseDTO addCategory(Category category);
    CategoryResponseDTO updateCategory(Long id, Category category);
    void deleteCategory(Long id);

    CategoryResponseDTO getCategoryById(Long id);

    CategoryResponseDTO getCategoryByName(String name);
}
