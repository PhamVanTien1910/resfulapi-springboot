package com.springboot.service;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.springboot.dto.authentication.request.AuthenticationRequest;
import com.springboot.dto.authentication.request.IntrospectRequest;
import com.springboot.dto.authentication.request.RefreshToken;
import com.springboot.dto.authentication.request.TokenRequest;
import com.springboot.dto.authentication.response.AuthenticationResponse;
import com.springboot.dto.authentication.response.IntrospectResponse;
import com.springboot.entity.UserEntity;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest input);

    String generateToken(UserEntity user);

    IntrospectResponse introspect(IntrospectRequest input) throws JOSEException, ParseException;

    void logout(TokenRequest request) throws JOSEException, ParseException;

    SignedJWT verifyToken(String token) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshToken request) throws JOSEException, ParseException;
}
