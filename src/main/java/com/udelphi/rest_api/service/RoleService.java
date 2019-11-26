package com.udelphi.rest_api.service;

import java.util.List;
import com.udelphi.rest_api.dto.RoleDto;
import com.udelphi.rest_api.exception.EntityNotFoundException;
import com.udelphi.rest_api.model.Role;
import com.udelphi.rest_api.repository.RoleRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public RoleDto saveRole(RoleDto roleDto) {
        Role saveRole = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(saveRole, RoleDto.class);
    }

    public RoleDto getRole(int id) {
        return roleRepository.findById(id)
                .map(role -> modelMapper.map(role, RoleDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<RoleDto> getAllCategories() {
        return roleRepository.findAll()
                .stream()
                .map( role -> modelMapper.map(role, RoleDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        roleRepository.findById(id).ifPresentOrElse(roleRepository::delete,
                () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }

    public void updateRole(int id, RoleDto roleDto) {
        roleRepository.findById(id)
                .map(role -> modelMapper.map(roleDto, Role.class))
                .ifPresentOrElse(roleRepository::save,
                        () -> {throw new EntityNotFoundException("Entity not found with id: " + id);});
    }
}
