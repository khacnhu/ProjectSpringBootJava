package com.example.DevProjectSpringBootJava.service;

import com.example.DevProjectSpringBootJava.dto.request.PermissionRequest;
import com.example.DevProjectSpringBootJava.dto.response.PermissionResponse;
import com.example.DevProjectSpringBootJava.entity.Permission;
import com.example.DevProjectSpringBootJava.mapper.toMapperPermission;
import com.example.DevProjectSpringBootJava.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = toMapperPermission.toPermissionReqMapPermission(permissionRequest);

        permission = permissionRepository.save(permission);

        return toMapperPermission.toPermissionMapPermissionRes(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissionList = permissionRepository.findAll();

        return permissionList.stream().map(toMapperPermission::toPermissionMapPermissionRes).toList();

    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }



}
