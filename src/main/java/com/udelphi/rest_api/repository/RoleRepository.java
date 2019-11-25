package com.udelphi.rest_api.repository;

import com.udelphi.rest_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
