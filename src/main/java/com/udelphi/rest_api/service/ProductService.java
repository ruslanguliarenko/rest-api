package com.udelphi.rest_api.service;

import java.util.List;
import com.udelphi.rest_api.model.Category;
import com.udelphi.rest_api.model.Product;
import com.udelphi.rest_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
