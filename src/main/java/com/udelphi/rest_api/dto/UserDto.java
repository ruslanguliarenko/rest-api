package com.udelphi.rest_api.dto;

import java.util.Set;

public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private Set<RoleDto> roles;

    public UserDto(){}

    public Integer getId() {
        return id;
    }

    public UserDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public UserDto setRoles(Set<RoleDto> roles) {
        this.roles = roles;
        return this;
    }
}
