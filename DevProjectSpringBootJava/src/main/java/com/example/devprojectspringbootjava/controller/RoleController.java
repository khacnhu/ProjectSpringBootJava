package com.example.devprojectspringbootjava.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.devprojectspringbootjava.dto.request.RoleRequest;
import com.example.devprojectspringbootjava.dto.response.ApiResponse;
import com.example.devprojectspringbootjava.dto.response.RoleResponse;
import com.example.devprojectspringbootjava.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

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
