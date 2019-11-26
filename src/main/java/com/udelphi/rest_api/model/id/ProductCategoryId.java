package com.udelphi.rest_api.model.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductCategoryId implements Serializable {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "category_id")
    private Integer categoryId;

    public ProductCategoryId(){}

    public ProductCategoryId(Integer productId, Integer categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
