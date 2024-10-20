package com.devchangetheworld.ewebsite.dto.cart_item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveCartItemRequestDTO {
    private Long cartId;
    private Long productId;
}
