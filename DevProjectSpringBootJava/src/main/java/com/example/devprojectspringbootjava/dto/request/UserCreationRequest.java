package com.example.devprojectspringbootjava.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.example.devprojectspringbootjava.validator.DobConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;
    private String lastName;

    @DobConstraint(min = 5, message = "ERROR_VALIDATION_DOB")
    private LocalDate dob;
}
