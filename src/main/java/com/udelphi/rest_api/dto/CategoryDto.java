package com.udelphi.rest_api.dto;

public class CategoryDto {

    private Integer id;
    private String name;

    public CategoryDto(){}

    public Integer getId() {
        return id;
    }

    public CategoryDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }
}
