package com.udelphi.rest_api.repository;

import com.udelphi.rest_api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
