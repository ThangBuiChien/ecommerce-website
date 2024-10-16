package com.devchangetheworld.ewebsite.mapper;

import com.devchangetheworld.ewebsite.dto.cart.CartResponseDTO;
import com.devchangetheworld.ewebsite.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AutoCartItemMapper.class})
public interface AutoCartMapper {

    CartResponseDTO toResponse(Cart cart);
}
