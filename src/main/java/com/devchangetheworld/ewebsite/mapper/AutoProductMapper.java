package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.request.AddProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.request.UpdateProductRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.ProductResponseDTO;
import com.devchangetheworld.ewebsite.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperService.class, AutoMapperPicture.class})
public interface AutoProductMapper {

    @Mapping(target = "category", source = "categoryName")
    Product toEntity(AddProductRequestDTO addProductRequestDTO);

    @Mapping(target = "category", source = "categoryName")
    Product toEntity(UpdateProductRequestDTO updateProductRequestDTO);

    @Mapping(target = "categoryName", source = "category.name")
    ProductResponseDTO toResponseDTO(Product product);
}
