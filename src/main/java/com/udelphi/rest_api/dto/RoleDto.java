package com.udelphi.rest_api.dto;

public class RoleDto {
    private Integer id;
    private String name;

    public RoleDto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
