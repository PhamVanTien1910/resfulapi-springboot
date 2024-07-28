package com.springboot.service.impl;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.springboot.dto.authentication.request.AuthenticationRequest;
import com.springboot.dto.authentication.request.IntrospectRequest;
import com.springboot.dto.authentication.request.RefreshToken;
import com.springboot.dto.authentication.request.TokenRequest;
import com.springboot.dto.authentication.response.AuthenticationResponse;
import com.springboot.dto.authentication.response.IntrospectResponse;
import com.springboot.entity.InvalidatedTokenEntity;
import com.springboot.entity.UserEntity;
import com.springboot.exception.AppException;
import com.springboot.exception.ErrorCode;
import com.springboot.repository.InvalidatedTokenRepository;
import com.springboot.repository.UserRepository;
import com.springboot.service.IAuthenticationService;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvalidatedTokenRepository tokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest input) {
        UserEntity userEntity = new UserEntity();
        userEntity = userRepository
                .findByUserName(input.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(input.getPassWord(), userEntity.getPassword());

        if (authenticated == true) {
            String token = generateToken(userEntity);
            AuthenticationResponse authenticationOutput = new AuthenticationResponse();
            authenticationOutput.setToken(token);
            return authenticationOutput;

        } else {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public String generateToken(UserEntity user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("dev.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot crate token", e);
            throw new RuntimeException();
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest input) throws JOSEException, ParseException {
        IntrospectResponse introspectOutput = new IntrospectResponse();
        String token = input.getToken();

        try {
            verifyToken(token);
        } catch (AppException e) {
            introspectOutput.setValid(false);
            return introspectOutput;
        }

        introspectOutput.setValid(true);
        return introspectOutput;
    }

    private String buildScope(UserEntity user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!org.springframework.util.CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add(role.getName());
                if (!org.springframework.util.CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    @Override
    public void logout(TokenRequest request) throws JOSEException, ParseException {
        SignedJWT signToken = verifyToken(request.getToken());

        String jti = signToken.getJWTClaimsSet().getJWTID();
        Date expirytime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedTokenEntity invalidatedTokenEntity =
                InvalidatedTokenEntity.builder().id(jti).expirytime(expirytime).build();
        tokenRepository.save(invalidatedTokenEntity);
    }

    @Override
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);

        if (!(verified && expityTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (tokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshToken request) throws JOSEException, ParseException {
        SignedJWT signedJWT = verifyToken(request.getToken());
        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        Date expirytime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedTokenEntity invalidatedTokenEntity =
                InvalidatedTokenEntity.builder().id(jti).expirytime(expirytime).build();
        tokenRepository.save(invalidatedTokenEntity);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        UserEntity user =
                userRepository.findByUserName(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
        String token = generateToken(user);
        AuthenticationResponse authenticationOutput = new AuthenticationResponse();
        authenticationOutput.setToken(token);
        return authenticationOutput;
    }
}
