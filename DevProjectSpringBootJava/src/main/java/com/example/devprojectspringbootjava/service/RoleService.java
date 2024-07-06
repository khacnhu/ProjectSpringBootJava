package com.example.devprojectspringbootjava.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devprojectspringbootjava.dto.request.RoleRequest;
import com.example.devprojectspringbootjava.dto.response.RoleResponse;
import com.example.devprojectspringbootjava.entity.Permission;
import com.example.devprojectspringbootjava.entity.Role;
import com.example.devprojectspringbootjava.mapper.ToRoleMapper;
import com.example.devprojectspringbootjava.repository.PermissionRepository;
import com.example.devprojectspringbootjava.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public RoleResponse createRole(RoleRequest request) {
        Role role = ToRoleMapper.RoleReqMapRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        role.setPermissions(new HashSet<>(permissions)); // new HashSet<Permission>(permissions)

        roleRepository.save(role);

        return ToRoleMapper.RoleMapRoleResponse(role);
    }

    public List<RoleResponse> getAllRole() {
        var roles = roleRepository.findAll();
        return roles.stream().map(ToRoleMapper::RoleMapRoleResponse).toList();
    }

    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }
}
