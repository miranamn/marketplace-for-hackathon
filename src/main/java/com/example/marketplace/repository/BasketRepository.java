package com.example.marketplace.repository;

import com.example.marketplace.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, UUID> {
}
