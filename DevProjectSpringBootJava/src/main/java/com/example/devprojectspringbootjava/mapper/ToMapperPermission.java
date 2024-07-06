package com.example.devprojectspringbootjava.mapper;

import com.example.devprojectspringbootjava.dto.request.PermissionRequest;
import com.example.devprojectspringbootjava.dto.response.PermissionResponse;
import com.example.devprojectspringbootjava.entity.Permission;

public class ToMapperPermission {

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
