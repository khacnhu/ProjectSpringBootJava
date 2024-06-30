package com.example.DevProjectSpringBootJava.service;


import com.example.DevProjectSpringBootJava.dto.request.AuthenticationRequest;
import com.example.DevProjectSpringBootJava.dto.request.IntrospectRequest;
import com.example.DevProjectSpringBootJava.dto.request.LogoutRequest;
import com.example.DevProjectSpringBootJava.dto.request.RefreshTokenRequest;
import com.example.DevProjectSpringBootJava.dto.response.AuthenticationResponse;
import com.example.DevProjectSpringBootJava.dto.response.IntrospectResponse;
import com.example.DevProjectSpringBootJava.entity.InvalidatedToken;
import com.example.DevProjectSpringBootJava.entity.User;
import com.example.DevProjectSpringBootJava.exception.ApiException;
import com.example.DevProjectSpringBootJava.exception.ErrorCode;
import com.example.DevProjectSpringBootJava.repository.InvalidatedTokenRepository;
import com.example.DevProjectSpringBootJava.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private InvalidatedTokenRepository invalidatedTokenRepository;

        @NonFinal
        @Value("${jwt.signerKey}")
        protected String SIGN_KEY;


        @NonFinal
        @Value("${jwt.valid-duration}")
        protected long VALID_DURATION;

        @NonFinal
        @Value("${jwt.refreshable-duration}")
        protected long REFRESHABLE_DURATION;



        public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {
            var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(
                    () -> new ApiException(ErrorCode.USER_NOT_EXISTED)
            );

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

            boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

            if(!authenticated)
                throw new ApiException(ErrorCode.AUTHENTICATED_EXCEPTION);

            var token = generateToken(user);
            return AuthenticationResponse.builder().accessToken(token).authenticated(true).build();


        };


        private String generateToken(User user) {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer("dev")
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                    ))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("scope", buildScope(user))
                    .build();

            Payload payload = new Payload(jwtClaimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            try {
                jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
                return jwsObject.serialize();
            } catch (JOSEException e) {
                throw new RuntimeException(e);
            }
        }

        public IntrospectResponse introspect(IntrospectRequest introspectRequest)
                throws JOSEException, ParseException {

            var token = introspectRequest.getAccessToken();
            boolean isValid = true;

            try {
                verifyToken(token, false);

            } catch (ApiException ex) {
                isValid = false;
            }

            System.out.println("check post " + isValid);

            return IntrospectResponse.builder().valid(
                    isValid
            ).build();
        }


        private String buildScope(User user) {
            StringJoiner stringJoiner = new StringJoiner(" ");

            if (!CollectionUtils.isEmpty(user.getRoles()))
                user.getRoles().forEach(role -> {
                    stringJoiner.add(role.getName());
                    if (!CollectionUtils.isEmpty(role.getPermissions()))
                        role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                });

            return stringJoiner.toString();


        }

        public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
            var signToken = verifyToken(logoutRequest.getToken(), false);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);

        }

        private SignedJWT verifyToken (String token, boolean isRefreshToken) throws JOSEException, ParseException {
            JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());

            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expiryTime = (isRefreshToken)
                    ? new Date(signedJWT.getJWTClaimsSet().getIssueTime()
                    .toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                    : signedJWT.getJWTClaimsSet().getExpirationTime();

            var verified = signedJWT.verify(verifier);

            if (!(verified && expiryTime.after(new Date()))) throw new ApiException(ErrorCode.AUTHENTICATED_EXCEPTION);

            if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
                throw new ApiException(ErrorCode.AUTHENTICATED_EXCEPTION);

            return signedJWT;
        }

        public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest)
                throws ParseException, JOSEException {
                System.out.println("service - refreshToken - authenController");

                var signJWT = verifyToken(refreshTokenRequest.getToken(), true);

                var jit = signJWT.getJWTClaimsSet().getJWTID();
                var expiredTime = signJWT.getJWTClaimsSet().getExpirationTime();

                InvalidatedToken invalidatedToken = new InvalidatedToken();
                invalidatedToken.setId(jit);
                invalidatedToken.setExpiryTime(expiredTime);
                invalidatedTokenRepository.save(invalidatedToken);

                var username = signJWT.getJWTClaimsSet().getSubject();

                System.out.println("check user name " + username);

                var user = userRepository.findByUsername(username).orElseThrow(
                        () ->  new ApiException(ErrorCode.USER_NOT_EXISTED)
                );

                var token = generateToken(user);
                return AuthenticationResponse.builder()
                        .accessToken(token)
                        .authenticated(true)
                        .build();

        }


}
