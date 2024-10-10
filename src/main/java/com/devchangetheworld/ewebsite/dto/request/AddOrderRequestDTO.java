package com.devchangetheworld.ewebsite.dto.request;

import com.devchangetheworld.ewebsite.entities.OrderItem;
import com.devchangetheworld.ewebsite.entities.User;
import com.devchangetheworld.ewebsite.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AddOrderRequestDTO {
    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private Set<OrderItem> orderItems;

    private Long userId;
}
