package com.udelphi.rest_api.service;

import java.util.List;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteById(int id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }

    public void updateCategory(int id, Category category) {
        categoryRepository.findById(id).ifPresentOrElse((result) -> categoryRepository.save(category),
                () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }
}
