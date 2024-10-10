package com.devchangetheworld.ewebsite.dto.response;

import com.devchangetheworld.ewebsite.entities.OrderItem;
import com.devchangetheworld.ewebsite.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private Set<OrderItemResponseDTO> orderItems;

    private Long userId;
}
