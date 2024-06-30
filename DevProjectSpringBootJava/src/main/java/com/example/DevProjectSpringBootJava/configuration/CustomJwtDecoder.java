package com.example.DevProjectSpringBootJava.configuration;

import com.example.DevProjectSpringBootJava.Service.AuthenticationService;
import com.example.DevProjectSpringBootJava.dto.request.IntrospectRequest;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        System.out.println("check chay vo decode custom ko");
        try {
            IntrospectRequest introspectRequest = new IntrospectRequest();
            introspectRequest.setAccessToken(token);

            var response = authenticationService.introspect(introspectRequest);

            System.out.println("check response intro " + response);

            if (!response.isValid())
                throw new JwtException("Token invalid");

        } catch (JOSEException | ParseException ex) {
            throw new JwtException(ex.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);


    }
}
