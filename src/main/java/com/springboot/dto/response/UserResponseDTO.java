package com.springboot.dto.response;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponseDTO {

    private String fullName;
    private String userName;
    private int status;
    //    private RoleEntity roles;
    private Set<RoleResponseDTO> role = new HashSet<>();
}
