package com.udelphi.rest_api.service;

import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategory(int id) {
        List<Category> categories  = categoryRepository.findAll();
        return categoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
