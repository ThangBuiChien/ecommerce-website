package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.request.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.OrderResponseDTO;
import com.devchangetheworld.ewebsite.entities.Order;

import java.util.Set;

public interface OrderService {

    OrderResponseDTO placeOrder(Long userId, Set<AddOrderItemRequestDTO> orderItemRequestDTOS);
    OrderResponseDTO getOrderById(Long orderId);
    OrderResponseDTO getAllOrdersByUser(Long userId);
}
