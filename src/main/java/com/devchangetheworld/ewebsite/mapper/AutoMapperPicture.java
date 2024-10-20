package com.devchangetheworld.ewebsite.mapper;


import com.devchangetheworld.ewebsite.dto.product.ImageResponseDTO;
import com.devchangetheworld.ewebsite.entities.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoMapperPicture {

    ImageResponseDTO toResponse(Image image);
}
