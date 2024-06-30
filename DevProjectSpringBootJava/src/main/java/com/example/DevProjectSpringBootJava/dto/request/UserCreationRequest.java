package com.example.DevProjectSpringBootJava.dto.request;

import com.example.DevProjectSpringBootJava.exception.ErrorCode;
import com.example.DevProjectSpringBootJava.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
