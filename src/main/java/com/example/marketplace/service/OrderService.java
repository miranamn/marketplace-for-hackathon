package com.example.marketplace.service;

import com.example.marketplace.entity.Order;
import com.example.marketplace.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public Optional<Order> getOrderById(UUID id){
        return orderRepository.findById(id);
    }
    @Transactional
    public UUID createOrder(Order order){
        return orderRepository.save(order).getOrderId();
    }
}
