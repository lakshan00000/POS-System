package com.ijse.pointofsalesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.pointofsalesystem.entity.Category;
import com.ijse.pointofsalesystem.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
       return categoryRepository.findAll(); 
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
        
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
   

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    

}
