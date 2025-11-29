package com.bramworks.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bramworks.inventory.model.Category;
import com.bramworks.inventory.responses.ResponseRest;
import com.bramworks.inventory.services.CategoryService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<ResponseRest> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ResponseRest> getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseRest> createCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<ResponseRest> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<ResponseRest> deleteCategory(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
