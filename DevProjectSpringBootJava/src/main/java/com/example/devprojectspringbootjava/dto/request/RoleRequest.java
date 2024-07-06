package com.example.devprojectspringbootjava.dto.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    private String name;
    private String description;
    private Set<String> permissions;
}
