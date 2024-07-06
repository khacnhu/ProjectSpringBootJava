package com.example.devprojectspringbootjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devprojectspringbootjava.dto.request.PermissionRequest;
import com.example.devprojectspringbootjava.dto.response.PermissionResponse;
import com.example.devprojectspringbootjava.entity.Permission;
import com.example.devprojectspringbootjava.mapper.ToMapperPermission;
import com.example.devprojectspringbootjava.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = ToMapperPermission.toPermissionReqMapPermission(permissionRequest);

        permission = permissionRepository.save(permission);

        return ToMapperPermission.toPermissionMapPermissionRes(permission);
    }

    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissionList = permissionRepository.findAll();

        return permissionList.stream()
                .map(ToMapperPermission::toPermissionMapPermissionRes)
                .toList();
    }

    public void deletePermission(String permission) {
        permissionRepository.deleteById(permission);
    }
}
