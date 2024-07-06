package com.example.devprojectspringbootjava.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.example.devprojectspringbootjava.entity.Role;

import lombok.Getter;
import lombok.Setter;

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
