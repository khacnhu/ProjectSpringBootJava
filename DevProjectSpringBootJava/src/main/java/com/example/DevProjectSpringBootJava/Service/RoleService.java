package com.example.DevProjectSpringBootJava.Service;


import com.example.DevProjectSpringBootJava.dto.request.RoleRequest;
import com.example.DevProjectSpringBootJava.dto.response.RoleResponse;
import com.example.DevProjectSpringBootJava.entity.Permission;
import com.example.DevProjectSpringBootJava.entity.Role;
import com.example.DevProjectSpringBootJava.mapper.toRoleMapper;
import com.example.DevProjectSpringBootJava.repository.PermissionRepository;
import com.example.DevProjectSpringBootJava.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request) {
        Role role = toRoleMapper.RoleReqMapRole(request);
        System.out.println("check request permission " + request.getPermissions());

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        System.out.println("check permissions " + permissions.toString());

        role.setPermissions(new HashSet<Permission>(permissions));

        roleRepository.save(role);

        return toRoleMapper.RoleMapRoleResponse(role);

    }


    public List<RoleResponse> getAllRole() {
        var roles = roleRepository.findAll();
        return roles.stream().map(toRoleMapper::RoleMapRoleResponse).toList();
    }

    public void deleteRole(String role) {
        roleRepository.deleteById(role);
    }

}
