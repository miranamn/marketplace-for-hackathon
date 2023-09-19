package com.example.marketplace.controller;

import com.example.marketplace.entity.Liked;
import com.example.marketplace.service.LikedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/liked")
public class LikedController {
    @Autowired
    private LikedService likedService;
    @GetMapping("/{id}")
    public Optional<Liked> getLikedById(@PathVariable("id") UUID id){
        return likedService.getLikedById(id);
    }
    @PostMapping
    public UUID createLiked(@RequestBody @Valid Liked liked){
        return likedService.createLiked(liked);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLiked(@PathVariable("id") UUID id){
        return likedService.deleteLiked(id);
    }
}
