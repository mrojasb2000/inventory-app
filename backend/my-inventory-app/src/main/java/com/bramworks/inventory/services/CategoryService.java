package com.bramworks.inventory.services;

import org.springframework.http.ResponseEntity;

import com.bramworks.inventory.responses.ResponseRest;

public interface CategoryService {

    public ResponseEntity<ResponseRest> getAll();

}
