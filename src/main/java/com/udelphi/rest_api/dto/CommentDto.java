package com.udelphi.rest_api.dto;

public class CommentDto {
    private Integer id;
    private String text;
    private UserDto user;
    private ProductDto product;
    private CommentDto comment;

    public CommentDto(){}

    public Integer getId() {
        return id;
    }

    public CommentDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentDto setText(String text) {
        this.text = text;
        return this;
    }

    public UserDto getUser() {
        return user;
    }

    public CommentDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public CommentDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public CommentDto getComment() {
        return comment;
    }

    public CommentDto setComment(CommentDto comment) {
        this.comment = comment;
        return this;
    }
}
