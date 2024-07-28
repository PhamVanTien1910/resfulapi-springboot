package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.converter.PermissionConverter;
import com.springboot.dto.request.PermissionRequestDTO;
import com.springboot.dto.response.PermissionResponseDTO;
import com.springboot.entity.PermissionEntity;
import com.springboot.repository.PermissionRepository;
import com.springboot.service.IPermissionService;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionConverter converter;

    @Override
    public PermissionResponseDTO create(PermissionRequestDTO request) {
        PermissionEntity permissionEntity = converter.toEntity(request);
        permissionEntity = permissionRepository.save(permissionEntity);
        return converter.toDTO(permissionEntity);
    }

    @Override
    public List<PermissionResponseDTO> getAll() {
        List<PermissionEntity> permissions = permissionRepository.findAll();
        return permissions.stream().map(converter::toDTO).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
