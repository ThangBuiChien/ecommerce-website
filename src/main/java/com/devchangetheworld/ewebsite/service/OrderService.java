package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.order.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.order.OrderResponseDTO;

import java.util.List;
import java.util.Set;

public interface OrderService {

    OrderResponseDTO placeOrder(Long userId, Set<AddOrderItemRequestDTO> orderItemRequestDTOS);
    OrderResponseDTO getOrderById(Long orderId);
    List<OrderResponseDTO> getAllOrdersByUser(Long userId);
}
