package com.bramworks.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private static final String ERROR_RESULT = "Result.error";
    private static final String ERROR_CREATE_CATEGORY = "Failed to create category";
    private static final String ERROR_UPDATE_CATEGORY = "Failed to update category";
    private static final String RESULT_OK = "Result.ok";
    private static final String MESSAGE_CATEGORIES_RETRIEVED = "Categories retrieved successfully";
    private static final String MESSAGE_CATEGORY_UPDATED = "Category updated successfully";
    private static final String MESSAGE_CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseRest> getAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        ResponseRest response = new ResponseRest();
        
        response.getCategoryResponse().setCategories(categories);
        response.setMetadata(RESULT_OK, "00", MESSAGE_CATEGORIES_RETRIEVED);
        log.info("Categories retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseRest> getById(Long id) {
        ResponseRest response = new ResponseRest();
        List<Category> categories = new ArrayList<>();

        Optional<Category> category = categoryRepository.findById(id);
       
        if (category.isEmpty()) {
            log.error("Category not found with id: {}", id);
            response.setMetadata(ERROR_RESULT, "99", MESSAGE_CATEGORY_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        categories.add(category.get());
        response.getCategoryResponse().setCategories(categories);
        response.setMetadata(RESULT_OK, "00", MESSAGE_CATEGORIES_RETRIEVED);
        log.info("Category retrieved successfully with id: {}", id);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseRest> create(Category category) {
        ResponseRest response = new ResponseRest();
        List<Category> categories = new ArrayList<>();

        try {
            Category savedCategory = categoryRepository.save(category);
            categories.add(savedCategory);

            if (savedCategory == null) {
                log.error(ERROR_CREATE_CATEGORY);
                response.setMetadata(ERROR_RESULT, "99", ERROR_CREATE_CATEGORY);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            response.getCategoryResponse().setCategories(categories);
            response.setMetadata(RESULT_OK, "00", "Category created successfully");
            
            log.info("Category created successfully with id: {}", savedCategory.getId());
        } catch (Exception e) {
            log.error("Error creating category: {}", e.getMessage());
            
            response.setMetadata(ERROR_RESULT, "99", ERROR_CREATE_CATEGORY);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseRest> update(Long id, Category category) {
        ResponseRest response = new ResponseRest();
        List<Category> categories = new ArrayList<>();

        try {
            Optional<Category> categorySaved = categoryRepository.findById(id);
       
            if (categorySaved.isEmpty()) {
                log.error("Category not found with id: {}", id);
                response.setMetadata(ERROR_RESULT, "99", MESSAGE_CATEGORY_NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            categorySaved.get().setName(category.getName());
            categorySaved.get().setDescription(category.getDescription());
            categoryRepository.save(categorySaved.get());
            categories.add(categorySaved.get());

            response.getCategoryResponse().setCategories(categories);
            response.setMetadata(RESULT_OK, "00", MESSAGE_CATEGORY_UPDATED);
                
            log.info("Category updated successfully with id: {}", categorySaved.get().getId());
        } catch (Exception e) {
            log.error("Error updating category: {}", e.getMessage());
            
            response.setMetadata(ERROR_RESULT, "99", ERROR_UPDATE_CATEGORY);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    
}
