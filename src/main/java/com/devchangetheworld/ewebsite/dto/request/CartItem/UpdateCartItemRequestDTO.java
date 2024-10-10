package com.devchangetheworld.ewebsite.dto.request.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemRequestDTO {
    private Long cartId;
    private Long productId;
    private int quality;
}
