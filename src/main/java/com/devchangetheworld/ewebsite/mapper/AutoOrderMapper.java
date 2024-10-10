package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.response.OrderResponseDTO;
import com.devchangetheworld.ewebsite.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AutoOrderItemMapper.class, AutoOrderItemMapper.class})
public interface AutoOrderMapper {

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDTO toResponseDTO(Order order);
}
