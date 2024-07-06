package com.example.devprojectspringbootjava.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.devprojectspringbootjava.dto.request.PermissionRequest;
import com.example.devprojectspringbootjava.dto.response.ApiResponse;
import com.example.devprojectspringbootjava.dto.response.PermissionResponse;
import com.example.devprojectspringbootjava.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

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
