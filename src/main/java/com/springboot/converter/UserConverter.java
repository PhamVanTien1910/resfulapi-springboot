package com.springboot.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.dto.request.UserRequestDTO;
import com.springboot.dto.response.RoleResponseDTO;
import com.springboot.dto.response.UserResponseDTO;
import com.springboot.entity.RoleEntity;
import com.springboot.entity.UserEntity;
import com.springboot.repository.RoleRepository;

@Component
public class UserConverter {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionConverter permissionConverter;

    @Autowired
    private RoleConverter roleConverter;

    public UserEntity toEntity(UserRequestDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setFullName(dto.getFullName());
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        Set<RoleEntity> roles = dto.getRoles().stream()
                .map(name -> roleRepository.findByName(name))
                .collect(Collectors.toSet());
        entity.setRoles(roles);
        return entity;
    }

    public UserResponseDTO toDTO(UserEntity entity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setFullName(entity.getFullName());
        dto.setUserName(entity.getUserName());
        dto.setStatus(entity.getStatus());
        Set<RoleResponseDTO> roles =
                entity.getRoles().stream().map(roleConverter::toDTO).collect(Collectors.toSet());
        dto.setRole(roles);
        return dto;
    }
    //	public RoleResponseDTO convertToRoleDTO(RoleEntity roleEntity) {
    //	    RoleResponseDTO roleDTO = new RoleResponseDTO();
    //	    roleDTO.setId(roleEntity.getId());
    //	    roleDTO.setName(roleEntity.getName());
    //	    return roleDTO;
    //	}

}
