package com.example.marketplace.service;

import com.example.marketplace.entity.Seller;
import com.example.marketplace.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
    @Transactional
    public ResponseEntity<String> addSellers(Seller seller){
        sellerRepository.save(seller);
        return ResponseEntity.ok("registration was success");
    }
}
