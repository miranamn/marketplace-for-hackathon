package com.example.marketplace.service;

import com.example.marketplace.entity.Liked;
import com.example.marketplace.repository.LikedRepository;
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
public class LikedService {
    @Autowired
    LikedRepository likedRepository;

    public Optional<Liked> getLikedById(UUID id){
        return likedRepository.findById(id);
    }
    @Transactional
    public UUID createLiked(Liked liked){
        return likedRepository.save(liked).getLikedId();
    }
    @Transactional
    public ResponseEntity<String> deleteLiked(UUID id){
        likedRepository.deleteById(id);
        return ResponseEntity.ok("DELETE was success");
    }
}
