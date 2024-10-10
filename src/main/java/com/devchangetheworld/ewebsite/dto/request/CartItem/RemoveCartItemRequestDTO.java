package com.devchangetheworld.ewebsite.dto.request.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveCartItemRequestDTO {
    private Long cartId;
    private Long productId;
}
