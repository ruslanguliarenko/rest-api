package com.udelphi.rest_api.controller;

import com.udelphi.rest_api.model.Role;
import com.udelphi.rest_api.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @GetMapping("/{id}")
    public Role getCategory(@PathVariable int id){
        return roleService.getCategory(id);
    }

    @GetMapping
    public List<Role>  getAllRoles(){
        return roleService.getAllRole();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRole(@PathVariable int id, @RequestBody Role role){
        roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable int id){
        roleService.deleteRole(id);
    }



}
