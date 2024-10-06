package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.response.CategoryResponseDTO;
import com.devchangetheworld.ewebsite.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AutoProductMapper.class})
public interface AutoCategoryMapper {

    @Mapping(target = "products", source = "products")
    CategoryResponseDTO toResponseDTO(Category category);
}
