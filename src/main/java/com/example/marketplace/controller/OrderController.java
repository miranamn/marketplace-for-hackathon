package com.example.marketplace.controller;

import com.example.marketplace.entity.Order;
import com.example.marketplace.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable("id") UUID id){
        return orderService.getOrderById(id);
    }
    @PostMapping
    public UUID createOrder(@RequestBody @Valid Order order){
        return orderService.createOrder(order);
    }
}
