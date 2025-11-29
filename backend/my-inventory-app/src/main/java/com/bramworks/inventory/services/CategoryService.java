package com.bramworks.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bramworks.inventory.model.Category;
import com.bramworks.inventory.responses.ResponseRest;

public interface CategoryService {

    public ResponseEntity<ResponseRest> getAll();

    public ResponseEntity<ResponseRest> getById(Long id);

    public ResponseEntity<ResponseRest> create(Category category);

}
