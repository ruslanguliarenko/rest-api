package com.udelphi.rest_api.dto;

import java.util.Set;

import java.sql.Date;

public class OrderDto {
    private Integer id;
    private UserDto client;
    private Date orderDate;
    private Set<OrderItemDto> orderItems;

    public OrderDto(){}

    public Integer getId() {
        return id;
    }

    public OrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserDto getClient() {
        return client;
    }

    public OrderDto setClient(UserDto client) {
        this.client = client;
        return this;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderDto setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public OrderDto setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
