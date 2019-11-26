package com.udelphi.rest_api.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private int id;
    private String name;
    private String description;
    private double price;
    private Set<CategoryDto> categories = new HashSet<>();

    public ProductDto(){}

    public int getId() {
        return id;
    }

    public ProductDto setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public ProductDto setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }
}
