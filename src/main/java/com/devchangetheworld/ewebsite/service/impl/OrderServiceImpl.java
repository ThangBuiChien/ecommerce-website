package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.order.AddOrderItemRequestDTO;
import com.devchangetheworld.ewebsite.dto.order.OrderResponseDTO;
import com.devchangetheworld.ewebsite.entities.Order;
import com.devchangetheworld.ewebsite.entities.OrderItem;
import com.devchangetheworld.ewebsite.entities.User;
import com.devchangetheworld.ewebsite.enums.OrderStatus;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoOrderItemMapper;
import com.devchangetheworld.ewebsite.mapper.AutoOrderMapper;
import com.devchangetheworld.ewebsite.repository.OrderItemRepository;
import com.devchangetheworld.ewebsite.repository.OrderRepository;
import com.devchangetheworld.ewebsite.repository.UserRepository;
import com.devchangetheworld.ewebsite.service.EmailService;
import com.devchangetheworld.ewebsite.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final AutoOrderItemMapper autoOrderItemMapper;
    private final AutoOrderMapper autoOrderMapper;

    private final EmailService emailService;

    @Transactional
    @Override
    public OrderResponseDTO placeOrder(Long userId, Set<AddOrderItemRequestDTO> orderItemRequestDTOS) {
        //Create new orders with user
        //Create new set<OrderItem> then add it to order
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        Order order = createOrder(user);
        Set<OrderItem> orderItems =  createOrderItem(orderItemRequestDTOS, order);

        order.setOrderItems(orderItems);
        order.setTotalAmount(calculateTotalAmount(orderItems));

        orderRepository.save(order);

        // Send email notification
        emailService.sendSimpleEmail(user.getEmail(),
                "Order Confirmation",
                "Your order has been placed successfully! with Id:" + order.getId());

        return autoOrderMapper.toResponseDTO(order);

    }

    private Order createOrder(User user){
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setUser(user);
        return order;
    }
    private Set<OrderItem> createOrderItem(Set<AddOrderItemRequestDTO> orderItemRequestDTOS, Order order){
        return orderItemRequestDTOS.stream()
                .map(autoOrderItemMapper::toEntity)
                .peek(orderItem -> {
                    orderItem.setOrder(order);
                    orderItem.setUnitPrice(orderItem.getProduct().getPrice());
                })
//                .map(orderItemRepository::save) //Don't need to save as cascade.all in orders
                .collect(Collectors.toSet());
    }

    private BigDecimal calculateTotalAmount(Set<OrderItem> orderItemList) {
        return  orderItemList
                .stream()
                .map(item -> item.getUnitPrice()
                        .multiply(new BigDecimal(item.getQuality())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId).map(autoOrderMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("order", "id", orderId));
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        return orderRepository.findAllByUserId(userId).stream()
                .map(autoOrderMapper::toResponseDTO)
                .toList();

    }
}
