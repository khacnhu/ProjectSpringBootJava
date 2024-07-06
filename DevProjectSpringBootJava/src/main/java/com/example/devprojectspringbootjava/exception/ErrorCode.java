package com.example.devprojectspringbootjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized_exception error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXIST(1001, "Account is created by other user", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be have at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be have at least 8 characters", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1005, "INVALID_KEY Exception Error", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006, "USER IS NOT EXISTED", HttpStatus.NOT_FOUND),
    AUTHENTICATED_EXCEPTION(1007, "UNAUTHENTICATED Exception error", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_EXCEPTION(1008, "You don't have permission", HttpStatus.FORBIDDEN),
    ERROR_VALIDATION_DOB(1009, "You must be at least {min}", HttpStatus.BAD_REQUEST);

    private final int code;
    private final HttpStatusCode statusCode;
    private final String message;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
