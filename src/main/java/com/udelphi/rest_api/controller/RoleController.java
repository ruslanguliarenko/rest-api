package com.udelphi.rest_api.controller;

import java.util.List;
import com.udelphi.rest_api.dto.RoleDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto createRole(@RequestBody RoleDto roleDto){
        return roleService.saveRole(roleDto);
    }

    @GetMapping("/{id}")
    public RoleDto getRole(@PathVariable int id){
        return roleService.getRole(id);
    }

    @GetMapping
    public List<RoleDto> getCategories(){
        return roleService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable int id){
        roleService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRole(@PathVariable int id, @RequestBody RoleDto roleDto){
        roleService.updateRole(id, roleDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleException(EntityNotFoundException exception) {
        return exception.getMessage();
    }



}
