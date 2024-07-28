package com.springboot.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.converter.UserConverter;
import com.springboot.dto.request.UserRequestDTO;
import com.springboot.dto.response.UserResponseDTO;
import com.springboot.entity.UserEntity;
import com.springboot.enums.Role;
import com.springboot.exception.AppException;
import com.springboot.exception.ErrorCode;
import com.springboot.repository.UserRepository;
import com.springboot.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO crateUser(UserRequestDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        if (userRepository.existsByUserName(userDTO.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userEntity = userConverter.toEntity(userDTO);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //		userEntity.setRole(roles);
        userEntity = userRepository.save(userEntity);
        return userConverter.toDTO(userEntity);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_UPDATE_DATA')")
    public List<UserResponseDTO> getUser() {
        List<UserEntity> user = userRepository.findAll();
        return user.stream().map(userConverter::toDTO).toList();
    }
}
