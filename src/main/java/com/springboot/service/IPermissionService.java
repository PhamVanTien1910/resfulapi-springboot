package com.springboot.service;

import java.util.List;

import com.springboot.dto.request.PermissionRequestDTO;
import com.springboot.dto.response.PermissionResponseDTO;

public interface IPermissionService {
    PermissionResponseDTO create(PermissionRequestDTO request);

    List<PermissionResponseDTO> getAll();

    void delete(String permission);
}
