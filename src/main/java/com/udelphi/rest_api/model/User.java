package com.udelphi.rest_api.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> roles;

    public User(String name, String email, Set<UserRole> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
