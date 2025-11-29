package com.bramworks.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bramworks.inventory.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
