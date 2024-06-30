package com.example.DevProjectSpringBootJava.controller;


import com.example.DevProjectSpringBootJava.dto.request.RefreshTokenRequest;
import com.example.DevProjectSpringBootJava.service.AuthenticationService;
import com.example.DevProjectSpringBootJava.dto.request.AuthenticationRequest;
import com.example.DevProjectSpringBootJava.dto.request.IntrospectRequest;
import com.example.DevProjectSpringBootJava.dto.request.LogoutRequest;
import com.example.DevProjectSpringBootJava.dto.response.ApiResponse;
import com.example.DevProjectSpringBootJava.dto.response.AuthenticationResponse;
import com.example.DevProjectSpringBootJava.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
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
                     var result = authenticationService.introspect(introspectRequest);
                     ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>();
                     apiResponse.setCode(200);
                     apiResponse.setData(result);

                     return apiResponse;
    }


   @PostMapping("/refreshToken")
   ApiResponse<AuthenticationResponse> refreshToken (@RequestBody RefreshTokenRequest refreshTokenRequest)
           throws ParseException, JOSEException {
       var result = authenticationService.refreshToken(refreshTokenRequest);
       ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
       apiResponse.setCode(200);
       apiResponse.setMessage("accessToken new have been generated by request refresh");
       apiResponse.setData(result);

       return apiResponse;
   }

}
