package com.example.marketplace.repository;

import com.example.marketplace.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID> {
    Optional<Seller> findBySellerEmail(String SellerEmail);
    Optional<Seller> findBySellerPhone(String SellerPhone);
    Optional<Seller> findBySellerNumber(String SellerNumber);
}
