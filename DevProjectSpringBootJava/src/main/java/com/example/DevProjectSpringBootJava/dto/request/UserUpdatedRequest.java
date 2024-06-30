package com.example.DevProjectSpringBootJava.dto.request;


import com.example.DevProjectSpringBootJava.entity.Role;
import com.example.DevProjectSpringBootJava.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserUpdatedRequest {

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;

    @DobConstraint(min = 3, message = "ERROR_VALIDATION_DOB")
    private LocalDate dob;

    private List<String> roles;
}
