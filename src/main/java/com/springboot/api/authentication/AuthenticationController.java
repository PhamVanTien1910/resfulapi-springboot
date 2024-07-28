package com.springboot.api.authentication;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.springboot.dto.authentication.request.AuthenticationRequest;
import com.springboot.dto.authentication.request.IntrospectRequest;
import com.springboot.dto.authentication.request.RefreshToken;
import com.springboot.dto.authentication.request.TokenRequest;
import com.springboot.dto.authentication.response.AuthenticationResponse;
import com.springboot.dto.authentication.response.IntrospectResponse;
import com.springboot.dto.response.ApiResponseDTO;
import com.springboot.service.impl.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponseDTO<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest input) {
        AuthenticationResponse result = authenticationService.authenticate(input);
        ApiResponseDTO<AuthenticationResponse> response = new ApiResponseDTO<>();
        response.setResult(result);
        return response;
    }

    @PostMapping("/introspect")
    ApiResponseDTO<IntrospectResponse> authenticate(@RequestBody IntrospectRequest input)
            throws JOSEException, ParseException {
        IntrospectResponse result = authenticationService.introspect(input);
        ApiResponseDTO<IntrospectResponse> response = new ApiResponseDTO<>();
        response.setResult(result);
        return response;
    }

    @PostMapping("/logout")
    ApiResponseDTO<Void> logout(@RequestBody TokenRequest input) throws JOSEException, ParseException {
        authenticationService.logout(input);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        return response;
    }

    @PostMapping("/refresh")
    ApiResponseDTO<AuthenticationResponse> refresh(@RequestBody RefreshToken input) throws JOSEException, ParseException {
        AuthenticationResponse authenticationOutput = authenticationService.refreshToken(input);
        ApiResponseDTO<AuthenticationResponse> response = new ApiResponseDTO<>();
        response.setResult(authenticationOutput);
        return response;
    }
}
