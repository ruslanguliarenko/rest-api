package com.udelphi.rest_api.controller;

import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }

}
