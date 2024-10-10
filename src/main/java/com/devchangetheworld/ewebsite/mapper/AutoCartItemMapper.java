package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.response.CartItemResponseDTO;
import com.devchangetheworld.ewebsite.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AutoProductMapper.class})
public interface AutoCartItemMapper {
    @Mapping(target = "cartId", source = "cart.id")
    CartItemResponseDTO toResponse(CartItem cartItem);

}
