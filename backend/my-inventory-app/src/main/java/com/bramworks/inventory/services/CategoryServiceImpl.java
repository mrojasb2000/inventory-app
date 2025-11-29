package com.bramworks.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bramworks.inventory.model.Category;
import com.bramworks.inventory.repository.CategoryRepository;
import com.bramworks.inventory.responses.ResponseRest;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseRest> getAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        ResponseRest response = new ResponseRest();
        
        response.getCategoryResponse().setCategories(categories);
        response.setMetadata("Result.ok", "00", "Categories retrieved successfully");
        
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseRest> getById(Long id) {
        ResponseRest response = new ResponseRest();
        List<Category> categories = new ArrayList<>();

        Optional<Category> category = categoryRepository.findById(id);
       
        if (category.isEmpty()) {
            response.setMetadata("Result.error", "99", "Category not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        categories.add(category.get());
        response.getCategoryResponse().setCategories(categories);
        response.setMetadata("Result.ok", "00", "Categories retrieved successfully");
        
        return ResponseEntity.ok(response);
    }
}
