package com.udelphi.rest_api.controller;

import java.util.List;
import com.udelphi.rest_api.dto.ProductDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDto> getCategories(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id){
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int id, @RequestBody ProductDto productDto){
        productService.updateProduct(id, productDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleException(EntityNotFoundException exception) {
        return exception.getMessage();
    }
}
