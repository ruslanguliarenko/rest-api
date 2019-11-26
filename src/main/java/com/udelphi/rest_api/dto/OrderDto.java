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

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getClient() {
        return client;
    }

    public void setClient(UserDto client) {
        this.client = client;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderItemDto> getOrderItemDto() {
        return orderItems;
    }

    public void setOrderItemDto(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
