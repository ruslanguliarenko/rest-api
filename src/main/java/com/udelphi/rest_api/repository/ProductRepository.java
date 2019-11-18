package com.udelphi.rest_api.repository;

import com.udelphi.rest_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
