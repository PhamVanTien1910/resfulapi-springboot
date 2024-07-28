package com.springboot.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(210, "User existed", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(250, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(230, "User must be at least 4 character", HttpStatus.BAD_GATEWAY),
    PASSWORD_INVALID(220, "Password must be at least 8 character", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(210, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(250, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(260, "You do not have permission ", HttpStatus.FORBIDDEN);

    private int code;
    private String message;
    private HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
