package com.example.marketplace.controller;

import com.example.marketplace.entity.Store;
import com.example.marketplace.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @GetMapping
    public List<Store> getAllStores(){
        return storeService.getAllStores();
    }
    @GetMapping("/{id}")
    public Optional<Store> getStore(@PathVariable("id") UUID id){
        return storeService.getStore(id);
    }
    @PostMapping
    public UUID createStore(@RequestBody @Valid Store store){
        return storeService.createStore(store);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") UUID id){
        return storeService.deleteStore(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStore(@PathVariable("id") UUID id, @RequestBody @Valid Store store){
        return storeService.updateStore(id, store);
    }
}
