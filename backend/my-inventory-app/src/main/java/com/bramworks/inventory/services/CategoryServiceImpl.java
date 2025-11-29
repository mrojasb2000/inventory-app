package com.bramworks.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
