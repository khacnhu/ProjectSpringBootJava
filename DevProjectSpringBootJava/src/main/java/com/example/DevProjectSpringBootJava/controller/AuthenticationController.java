package com.example.DevProjectSpringBootJava.controller;


import com.example.DevProjectSpringBootJava.Service.AuthenticationService;
import com.example.DevProjectSpringBootJava.dto.request.AuthenticationRequest;
import com.example.DevProjectSpringBootJava.dto.request.IntrospectRequest;
import com.example.DevProjectSpringBootJava.dto.request.LogoutRequest;
import com.example.DevProjectSpringBootJava.dto.response.ApiResponse;
import com.example.DevProjectSpringBootJava.dto.response.AuthenticationResponse;
import com.example.DevProjectSpringBootJava.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("check co chay vo ko");

        var result = authenticationService.authentication(authenticationRequest);

        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setData(result);

        return apiResponse;

    }

    @PostMapping("/logout")
    ApiResponse<String> logout (@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);

        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setCode(200);
        apiResponse.setData("You are logged out successfully");

        return apiResponse;
    }


    @PostMapping("/intro")
    ApiResponse<IntrospectResponse> introspectResponseApiResponse (@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
                    System.out.println("check oke ko");
                     var result = authenticationService.introspect(introspectRequest);
                     ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
                     apiResponse.setCode(200);
                     apiResponse.setData(result);

                     return apiResponse;


    }


    @GetMapping("/post")
    String getNew() {
        return "hello";
    }


}
