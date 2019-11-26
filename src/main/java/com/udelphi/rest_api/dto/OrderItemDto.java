package com.udelphi.rest_api.dto;

public class OrderItemDto {
    private OrderDto order;
    private ProductDto product;
    private Integer quantity;
    public OrderItemDto(){}

    public OrderDto getOrder() {
        return order;
    }

    public OrderItemDto setOrder(OrderDto order) {
        this.order = order;
        return this;
    }

    public ProductDto getProduct() {
        return product;
    }

    public OrderItemDto setProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
