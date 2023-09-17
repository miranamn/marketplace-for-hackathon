package com.example.marketplace.controller;

import com.example.marketplace.entity.Position;
import com.example.marketplace.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/position")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @GetMapping
    public List<Position> getPosition(){
        return positionService.getPositions();
    }
    @PostMapping
    public UUID createPosition(@RequestBody @Valid Position position){
        return positionService.createPosition(position);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable("id") UUID id){
        return positionService.deletePosition(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePosition(@PathVariable("id") UUID id, @RequestBody @Valid Position position){
        return positionService.updatePosition(id, position);
    }


}
