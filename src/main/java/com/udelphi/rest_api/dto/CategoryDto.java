package com.udelphi.rest_api.dto;

public class CategoryDto {

    private Integer id;
    private String name;

    public CategoryDto(){}

    public CategoryDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
