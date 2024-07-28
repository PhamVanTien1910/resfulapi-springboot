package com.springboot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.dto.response.ApiResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponseDTO> handlingRuntimeException(RuntimeException exception) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponseDTO.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponseDTO);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponseDTO> handlingRuntimeException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setCode(errorCode.getCode());
        apiResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponseDTO);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponseDTO> handlingValidation(MethodArgumentNotValidException exception) {
        String enumkey = exception.getBindingResult().getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumkey);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setCode(errorCode.getCode());
        apiResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponseDTO);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponseDTO> handlingAccessDenied(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setCode(errorCode.getCode());
        apiResponseDTO.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponseDTO);
    }
}
