package com.bramworks.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bramworks.inventory.responses.ResponseRest;
import com.bramworks.inventory.services.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

}
