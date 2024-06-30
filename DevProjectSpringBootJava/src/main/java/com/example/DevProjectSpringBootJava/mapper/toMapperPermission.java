package com.example.DevProjectSpringBootJava.mapper;

import com.example.DevProjectSpringBootJava.dto.request.PermissionRequest;
import com.example.DevProjectSpringBootJava.dto.response.PermissionResponse;
import com.example.DevProjectSpringBootJava.entity.Permission;

public class toMapperPermission {
    public static Permission toPermissionReqMapPermission(PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        return permission;
    }

    public static PermissionResponse toPermissionMapPermissionRes(Permission permission) {
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setName(permission.getName());
        permissionResponse.setDescription(permission.getDescription());
        return permissionResponse;
    }

}
