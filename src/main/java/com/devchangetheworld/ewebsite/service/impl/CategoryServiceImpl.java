package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.response.CategoryResponseDTO;
import com.devchangetheworld.ewebsite.entities.Category;
import com.devchangetheworld.ewebsite.exception.ResourceAlreadyExitException;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoCategoryMapper;
import com.devchangetheworld.ewebsite.repository.CategoryRepository;
import com.devchangetheworld.ewebsite.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final AutoCategoryMapper autoCategoryMapper;

    @Override
    public List<CategoryResponseDTO> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(autoCategoryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Page<CategoryResponseDTO> getAllCategoryPageable(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(autoCategoryMapper::toResponseDTO);
    }

    @Override
    public CategoryResponseDTO addCategory(Category category) {
//        categoryRepository.findByName(category.getName())
//                .ifPresent(c -> {
//                    throw new ResourceAlreadyExitException("Category's name already existed");
//                });
//        return autoCategoryMapper.toResponseDTO(categoryRepository.save(category));

        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .map(autoCategoryMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceAlreadyExitException(category.getName()+" already exists"));
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, Category category) {
        Category oldCategory =  categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
        oldCategory.setName(category.getName());
        return autoCategoryMapper.toResponseDTO(categoryRepository.save(oldCategory));

    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> {throw new ResourceNotFoundException("Category", "id", id);
                        });
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(autoCategoryMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
    }

    @Override
    public CategoryResponseDTO getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(autoCategoryMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("category with name : " + name +  "not found"));
    }
}
