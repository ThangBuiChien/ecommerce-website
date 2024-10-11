package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.Cart;
import com.devchangetheworld.ewebsite.entities.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

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
