package com.springboot.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.dto.request.RoleRequestDTO;
import com.springboot.dto.response.PermissionResponseDTO;
import com.springboot.dto.response.RoleResponseDTO;
import com.springboot.entity.RoleEntity;

@Component
public class RoleConverter {

    @Autowired
    private PermissionConverter permissionConverter;

    public RoleEntity toEntity(RoleRequestDTO request) {
        RoleEntity entity = new RoleEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public RoleResponseDTO toDTO(RoleEntity entity) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        Set<PermissionResponseDTO> permissionDTO =
                entity.getPermissions().stream().map(permissionConverter::toDTO).collect(Collectors.toSet());
        dto.setPermissions(permissionDTO);
        return dto;
    }

    //	public RoleResponseDTO convertToRoleDTO(RoleEntity roleEntity) {
    //	    RoleResponseDTO roleDTO = new RoleResponseDTO();
    //	    roleDTO.setId(roleEntity.getId());
    //	    roleDTO.setName(roleEntity.getName());
    //	    return roleDTO;
    //	}

}
