package com.springboot.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.converter.RoleConverter;
import com.springboot.dto.request.RoleRequestDTO;
import com.springboot.dto.response.RoleResponseDTO;
import com.springboot.entity.PermissionEntity;
import com.springboot.entity.RoleEntity;
import com.springboot.repository.PermissionRepository;
import com.springboot.repository.RoleRepository;
import com.springboot.service.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleConverter converter;

    @Override
    public RoleResponseDTO create(RoleRequestDTO request) {
        RoleEntity entity = converter.toEntity(request);
        List<PermissionEntity> permissions = permissionRepository.findAllById(request.getPermissions());
        entity.setPermissions(new HashSet<>(permissions));
        entity = roleRepository.save(entity);
        return converter.toDTO(entity);
    }

    @Override
    public List<RoleResponseDTO> getAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roles.stream().map(converter::toDTO).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
