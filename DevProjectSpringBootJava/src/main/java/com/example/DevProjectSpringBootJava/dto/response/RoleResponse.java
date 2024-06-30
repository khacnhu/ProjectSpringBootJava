package com.example.DevProjectSpringBootJava.dto.response;

import com.example.DevProjectSpringBootJava.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RoleResponse {

    private String name;
    private String description;
    Set<Permission> permissions;

}
