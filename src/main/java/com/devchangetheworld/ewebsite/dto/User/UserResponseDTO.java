package com.devchangetheworld.ewebsite.dto.User;

import com.devchangetheworld.ewebsite.dto.cart.CartResponseDTO;
import com.devchangetheworld.ewebsite.dto.order.OrderResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private CartResponseDTO cart;
    private List<OrderResponseDTO> orders;
}
