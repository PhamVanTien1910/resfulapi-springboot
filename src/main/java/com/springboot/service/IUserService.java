package com.springboot.service;

import java.util.List;

import com.springboot.dto.request.UserRequestDTO;
import com.springboot.dto.response.UserResponseDTO;

public interface IUserService {
    UserResponseDTO crateUser(UserRequestDTO userDTO);

    List<UserResponseDTO> getUser();
}
