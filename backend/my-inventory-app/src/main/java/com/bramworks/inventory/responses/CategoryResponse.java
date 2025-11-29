package com.bramworks.inventory.responses;

import java.util.ArrayList;
import java.util.List;

import com.bramworks.inventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
    private List<Category> categories = new ArrayList<>();
}
