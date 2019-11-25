package com.udelphi.rest_api.controller;

import java.util.List;
import com.udelphi.rest_api.dto.CategoryDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
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
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }

    @GetMapping
    public List<CategoryDto> getCategories(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(id, categoryDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleException(EntityNotFoundException exception) {
        return exception.getMessage();
    }
}
