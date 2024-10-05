package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.entities.Category;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperService.class);

    private final CategoryRepository categoryRepository;


    public Category findCategoryByName(String name){
        //find by name or created if not existed
        return  categoryRepository.findByName(name)
                .orElseGet(() -> {
                    LOGGER.info("Created new category successfully");
                    Category category = new Category();
                    category.setName(name);
                    return categoryRepository.save((category));
                });
    }
}
