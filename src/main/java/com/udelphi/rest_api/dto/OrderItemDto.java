package com.udelphi.rest_api.dto;

public class OrderItemDto {
    private OrderDto order;
    private ProductDto product;
    private Integer quantity;
    public OrderItemDto(){}

    public OrderDto getOrderDto() {
        return order;
    }

    public void setOrderDto(OrderDto order) {
        this.order = order;
    }

    public ProductDto getProductDto() {
        return product;
    }

    public void setProductDto(ProductDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
