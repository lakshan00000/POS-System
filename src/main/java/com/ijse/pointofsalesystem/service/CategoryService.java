package com.ijse.pointofsalesystem.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Category;

@Service
public interface CategoryService {

    List<Category> getCategories();
    Category createCategory(Category category);
    Category getCategoryById(Long id );
    void deleteCategory(Long categoryId);

    
}
