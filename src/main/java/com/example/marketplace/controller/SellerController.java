package com.example.marketplace.controller;

import com.example.marketplace.entity.Seller;
import com.example.marketplace.entity.User;
import com.example.marketplace.service.SellerService;
import com.example.marketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @GetMapping
    public List<Seller> getSellers(){
        return sellerService.getAllSellers();
    }
    @PostMapping
    public ResponseEntity<String> addSellers(@RequestBody @Valid Seller seller){
        return sellerService.addSellers(seller);
    }
}
