package com.example.marketplace.controller;

import com.example.marketplace.entity.Seller;
import com.example.marketplace.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @GetMapping
    public List<Seller> getSellers(){
        return sellerService.getAllSellers();
    }
    @GetMapping("/{id}")
    public Optional<Seller> getSellerById(@PathVariable("id") UUID id){
        return sellerService.getSellerById(id);
    }
    @PostMapping("/registration")
    public UUID addSellers(@RequestBody @Valid Seller seller){
        return sellerService.addSellers(seller);
    }
    @PostMapping("/login")
    public Map<String, Object> loginSeller(@RequestBody @Valid Map<String, String> seller){
        return sellerService.loginSeller(seller);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSeller(@PathVariable("id") UUID id, @RequestBody @Valid Seller seller){
        return sellerService.updateSeller(id, seller);
    }
}
