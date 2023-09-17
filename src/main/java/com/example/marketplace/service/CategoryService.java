package com.example.marketplace.service;

import com.example.marketplace.entity.Category;
import com.example.marketplace.exceptions.ValidUserException;
import com.example.marketplace.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategory(UUID id){
        return categoryRepository.findById(id);
    }
    @Transactional
    public UUID createCategory(Category category){
        return categoryRepository.save(category).getCategoryId();
    }
    @Transactional
    public ResponseEntity<String> deleteCategory(UUID id){
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("DELETE was success");
    }
    @Transactional
    public ResponseEntity<String> updateCategory(UUID id, Category category){
        Category categoryTemp = this.categoryRepository.findById(id).orElseThrow(() -> new ValidUserException(
                "category with id " + id + " does not exists"));
        categoryTemp.setCategoryName(category.getCategoryName());
        categoryTemp.setCategoryPhoto(category.getCategoryPhoto());
        categoryTemp.setPositions(category.getPositions());
        return ResponseEntity.ok("update was success");
    }
}
