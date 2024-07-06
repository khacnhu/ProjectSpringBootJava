package com.example.devprojectspringbootjava.dto.response;

import java.util.Set;

import com.example.devprojectspringbootjava.entity.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {

    private String name;
    private String description;
    Set<Permission> permissions;
}
