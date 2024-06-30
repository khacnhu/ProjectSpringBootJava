package com.example.DevProjectSpringBootJava.dto.response;

import com.example.DevProjectSpringBootJava.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
public class UserResponse {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    private Set<Role> roles;

}
