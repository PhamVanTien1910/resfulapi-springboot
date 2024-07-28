package com.springboot.dto.response;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    private String name;
    private String description;
    Set<PermissionResponseDTO> permissions = new HashSet<>();
}
