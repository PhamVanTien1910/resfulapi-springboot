package com.springboot.api;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.request.UserRequestDTO;
import com.springboot.dto.response.ApiResponseDTO;
import com.springboot.dto.response.UserResponseDTO;
import com.springboot.service.impl.UserService;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    ApiResponseDTO<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO model) {
        ApiResponseDTO<UserResponseDTO> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(userService.crateUser(model));
        return apiResponse;
    }

    @GetMapping(value = "/user")
    public ApiResponseDTO<List<UserResponseDTO>> getUser() {
        ApiResponseDTO<List<UserResponseDTO>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setResult(userService.getUser());
        return apiResponse;
    }
}
