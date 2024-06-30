package com.example.DevProjectSpringBootJava.controller;


import com.example.DevProjectSpringBootJava.Service.PermissionService;
import com.example.DevProjectSpringBootJava.dto.request.PermissionRequest;
import com.example.DevProjectSpringBootJava.dto.response.ApiResponse;
import com.example.DevProjectSpringBootJava.dto.response.PermissionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
        ApiResponse<PermissionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("create permission successfully");
        apiResponse.setData(permissionService.createPermission(permissionRequest));
        return apiResponse;
    }


    @GetMapping
    ApiResponse<List<PermissionResponse>> getAllPer() {
        ApiResponse<List<PermissionResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("All List of Permission");
        apiResponse.setData(permissionService.getAllPermissions());
        return apiResponse;
    }

    @GetMapping("/{permission}")
    ApiResponse<Void> getDetailPer(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Detail permission");
        apiResponse.setData(null);
        return apiResponse;
    }

}
