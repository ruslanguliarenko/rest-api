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

    public ProductDto setId(int id) {
        this.id = id;
        return this;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductDto setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }
}
