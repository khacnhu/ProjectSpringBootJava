package com.example.DevProjectSpringBootJava.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private boolean authenticated;
    private String accessToken;
}
