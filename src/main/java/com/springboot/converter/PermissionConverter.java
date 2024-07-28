package com.springboot.converter;

import org.springframework.stereotype.Component;

import com.springboot.dto.request.PermissionRequestDTO;
import com.springboot.dto.response.PermissionResponseDTO;
import com.springboot.entity.PermissionEntity;

@Component
public class PermissionConverter {
    public PermissionEntity toEntity(PermissionRequestDTO dto) {
        PermissionEntity entity = new PermissionEntity();
        entity.setName(dto.getName());
        entity.setDesciption(dto.getDescription());
        return entity;
    }

    public PermissionResponseDTO toDTO(PermissionEntity entity) {
        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDesciption());
        return dto;
    }
    //	public RoleResponseDTO convertToRoleDTO(RoleEntity roleEntity) {
    //	    RoleResponseDTO roleDTO = new RoleResponseDTO();
    //	    roleDTO.setId(roleEntity.getId());
    //	    roleDTO.setName(roleEntity.getName());
    //	    return roleDTO;
    //	}

}
