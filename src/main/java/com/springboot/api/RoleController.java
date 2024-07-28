package com.springboot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.request.RoleRequestDTO;
import com.springboot.dto.response.ApiResponseDTO;
import com.springboot.dto.response.RoleResponseDTO;
import com.springboot.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    ApiResponseDTO<RoleResponseDTO> create(@RequestBody RoleRequestDTO request) {
        ApiResponseDTO<RoleResponseDTO> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(roleService.create(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponseDTO<List<RoleResponseDTO>> getAll() {
        ApiResponseDTO<List<RoleResponseDTO>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(roleService.getAll());
        return apiResponse;
    }

    @DeleteMapping("/{role}")
    ApiResponseDTO<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponseDTO.<Void>builder().build();
    }
}
