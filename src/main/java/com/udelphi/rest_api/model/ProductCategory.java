package com.udelphi.rest_api.model;

import com.udelphi.rest_api.model.id.ProductCategoryId;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @EmbeddedId
    private ProductCategoryId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId( "categoryId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public ProductCategory(ProductCategoryId id, Product product, Category category){
        this.id = id;
        this.product = product;
        this.category = category;
    }
    public ProductCategory(){}



    public ProductCategoryId getId() {
        return id;
    }

    public void setId(ProductCategoryId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
