package com.example.marketplace.repository;

import com.example.marketplace.entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LikedRepository extends JpaRepository<Liked, UUID> {
}
