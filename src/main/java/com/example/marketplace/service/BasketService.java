package com.example.marketplace.service;

import com.example.marketplace.entity.Basket;
import com.example.marketplace.repository.BasketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class BasketService {
    @Autowired
    BasketRepository basketRepository;
    public Optional<Basket> getBasketById(UUID id){
        return basketRepository.findById(id);
    }
    @Transactional
    public UUID createBasket(Basket basket){
        return basketRepository.save(basket).getBasketId();
    }
    @Transactional
    public ResponseEntity<String> deleteBasket(UUID id){
        basketRepository.deleteById(id);
        return ResponseEntity.ok("DELETE was success");
    }
}
