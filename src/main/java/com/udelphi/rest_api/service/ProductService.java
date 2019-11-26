package com.udelphi.rest_api.service;

import java.util.List;
import com.udelphi.rest_api.dto.ProductDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.model.Product;
import com.udelphi.rest_api.repository.ProductRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    public ProductDto saveProduct(ProductDto productDto) {

        Product saveProduct = productRepository.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(saveProduct, ProductDto.class);
    }

    public ProductDto getProduct(int id) {

        List<Product> products = productRepository.findAll();
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map( product -> modelMapper.map(product, ProductDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(int id, ProductDto productDto) {
        productRepository.findById(id)
                .map(product -> modelMapper.map(productDto, Product.class))
                .ifPresentOrElse(productRepository::save,
                        () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }
}
