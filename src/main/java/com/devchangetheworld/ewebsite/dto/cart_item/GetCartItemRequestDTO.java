package com.devchangetheworld.ewebsite.dto.cart_item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCartItemRequestDTO {
    private Long cartId;
    private Long productId;
}
