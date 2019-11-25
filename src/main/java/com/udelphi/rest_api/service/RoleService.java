package com.udelphi.rest_api.service;

import com.udelphi.rest_api.model.Role;
import com.udelphi.rest_api.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getCategory(int id) {
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public void updateRole(int id, Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
