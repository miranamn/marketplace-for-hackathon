package com.example.marketplace.service;

import com.example.marketplace.entity.Position;
import com.example.marketplace.exceptions.ValidUserException;
import com.example.marketplace.repository.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PositionService {
    @Autowired
    PositionRepository positionRepository;
    public List<Position> getPositions(){
        return positionRepository.findAll();
    }
    @Transactional
    public UUID createPosition(Position position){
        return positionRepository.save(position).getPositionId();
    }
    @Transactional
    public ResponseEntity<String> deletePosition(UUID id){
        positionRepository.deleteById(id);
        return ResponseEntity.ok("DELETE was success");
    }
    @Transactional
    public ResponseEntity<String> updatePosition(UUID id, Position position){
        Position positionTemp = this.positionRepository.findById(id).orElseThrow(() -> new ValidUserException(
                "position with id " + id + " does not exists"));
        positionTemp.setPositionCount(position.getPositionCount());
        positionTemp.setPositionDesc(position.getPositionDesc());
        positionTemp.setPositionName(position.getPositionName());
        positionTemp.setPositionPhoto(position.getPositionPhoto());
        positionTemp.setPositionPrice(position.getPositionPrice());
        return ResponseEntity.ok("update was success");
    }
}
