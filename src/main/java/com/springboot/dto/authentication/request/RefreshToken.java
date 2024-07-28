package com.springboot.dto.authentication.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RefreshToken {
    private String token;
}
