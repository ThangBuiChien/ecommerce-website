package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.order.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.order.OrderItemResponseDTO;
import com.devchangetheworld.ewebsite.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses={MapperService.class, AutoProductMapper.class})
public interface AutoOrderItemMapper {

    @Mapping(target = "product", source = "productId")
    OrderItem toEntity(AddOrderItemRequestDTO addOrderItemRequestDTO);

    OrderItemResponseDTO toResponse(OrderItem orderItem);
}
