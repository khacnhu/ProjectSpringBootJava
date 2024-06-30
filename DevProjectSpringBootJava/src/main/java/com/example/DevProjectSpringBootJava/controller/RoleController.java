package com.example.DevProjectSpringBootJava.controller;


import com.example.DevProjectSpringBootJava.service.RoleService;
import com.example.DevProjectSpringBootJava.dto.request.RoleRequest;
import com.example.DevProjectSpringBootJava.dto.response.ApiResponse;
import com.example.DevProjectSpringBootJava.dto.response.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Role create successfully");
        apiResponse.setData(roleService.createRole(request));
        return apiResponse;

    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {
        ApiResponse<List<RoleResponse>> listApiResponse = new ApiResponse<>();

        listApiResponse.setCode(200);
        listApiResponse.setData(roleService.getAllRole());
        return listApiResponse;

    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> getDetailRole(@PathVariable String role) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        roleService.deleteRole(role);
        apiResponse.setCode(200);
        apiResponse.setMessage("Delete role successfully");
        return apiResponse;
    }









}
