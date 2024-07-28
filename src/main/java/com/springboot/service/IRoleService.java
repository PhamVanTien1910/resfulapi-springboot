package com.springboot.service;

import java.util.List;

import com.springboot.dto.request.RoleRequestDTO;
import com.springboot.dto.response.RoleResponseDTO;

public interface IRoleService {
    RoleResponseDTO create(RoleRequestDTO request);

    List<RoleResponseDTO> getAll();

    void delete(String role);
}
