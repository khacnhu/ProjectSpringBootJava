package com.example.DevProjectSpringBootJava.mapper;

import com.example.DevProjectSpringBootJava.dto.request.RoleRequest;
import com.example.DevProjectSpringBootJava.dto.response.RoleResponse;
import com.example.DevProjectSpringBootJava.entity.Role;

public class toRoleMapper {


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
