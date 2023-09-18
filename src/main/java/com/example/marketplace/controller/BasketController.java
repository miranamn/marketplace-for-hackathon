package com.example.marketplace.controller;

import com.example.marketplace.entity.Basket;
import com.example.marketplace.service.BasketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;
    @GetMapping("/{id}")
    public Optional<Basket> getBasketById(@PathVariable("id") UUID id){
        return basketService.getBasketById(id);
    }
    @PostMapping
    public UUID createBasket(@RequestBody @Valid Basket basket){
        return basketService.createBasket(basket);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBasket(@PathVariable("id") UUID id){
        return basketService.deleteBasket(id);
    }

}
