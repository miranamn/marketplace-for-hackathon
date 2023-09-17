package com.example.marketplace.controller;

import com.example.marketplace.entity.Category;
import com.example.marketplace.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/{id}")
    public Optional<Category> getCategory(@PathVariable("id") UUID id){
        return categoryService.getCategory(id);
    }
    @PostMapping
    public UUID createCategory(@RequestBody @Valid Category category){
        return categoryService.createCategory(category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") UUID id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") UUID id, @RequestBody @Valid Category category){
        return categoryService.updateCategory(id, category);
    }
}
