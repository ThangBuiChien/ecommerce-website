package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.product.ImageS3ResponseDTO;
import com.devchangetheworld.ewebsite.entities.ImageS3;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutoMapperImageS3 {

    ImageS3ResponseDTO toResponse(ImageS3 imageS3);
}
