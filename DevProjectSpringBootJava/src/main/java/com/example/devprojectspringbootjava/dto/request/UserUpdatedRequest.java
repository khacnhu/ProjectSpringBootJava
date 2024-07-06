package com.example.devprojectspringbootjava.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Size;

import com.example.devprojectspringbootjava.validator.DobConstraint;

import lombok.Getter;
import lombok.Setter;

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
