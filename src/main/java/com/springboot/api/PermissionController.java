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

import com.springboot.dto.request.PermissionRequestDTO;
import com.springboot.dto.response.ApiResponseDTO;
import com.springboot.dto.response.PermissionResponseDTO;
import com.springboot.service.IPermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping
    ApiResponseDTO<PermissionResponseDTO> create(@RequestBody PermissionRequestDTO request) {
        ApiResponseDTO<PermissionResponseDTO> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(permissionService.create(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponseDTO<List<PermissionResponseDTO>> getAll() {
        ApiResponseDTO<List<PermissionResponseDTO>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(permissionService.getAll());
        return apiResponse;
    }

    @DeleteMapping("/{permission}")
    ApiResponseDTO<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponseDTO.<Void>builder().build();
    }
}
