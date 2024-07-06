package com.example.devprojectspringbootjava.mapper;

import com.example.devprojectspringbootjava.dto.request.RoleRequest;
import com.example.devprojectspringbootjava.dto.response.RoleResponse;
import com.example.devprojectspringbootjava.entity.Role;

public class ToRoleMapper {

    public static Role RoleReqMapRole(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        return role;
    }

    public static RoleResponse RoleMapRoleResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setPermissions(role.getPermissions());
        return response;
    }
}
