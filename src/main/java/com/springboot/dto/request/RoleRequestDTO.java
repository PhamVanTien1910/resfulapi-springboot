package com.springboot.dto.request;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
    private String name;
    private String description;
    Set<String> permissions = new HashSet<>();
}
