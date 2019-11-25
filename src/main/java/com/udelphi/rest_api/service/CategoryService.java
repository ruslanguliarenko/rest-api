package com.udelphi.rest_api.service;

import java.util.List;
import com.udelphi.rest_api.dto.CategoryDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.repository.CategoryRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    public CategoryDto getCategory(int id) {

        List<Category> categorys = categoryRepository.findAll();
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map( category -> modelMapper.map(category, CategoryDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }

    public void updateCategory(int id, CategoryDto categoryDto) {
       categoryRepository.findById(id)
               .map(category -> modelMapper.map(categoryDto, Category.class))
               .ifPresentOrElse(categoryRepository::save,
                       () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }
}
