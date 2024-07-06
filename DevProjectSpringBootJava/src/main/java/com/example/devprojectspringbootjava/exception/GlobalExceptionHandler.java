package com.example.devprojectspringbootjava.exception;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;

import jakarta.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.devprojectspringbootjava.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String MIN_DOB_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<String>> handlerRuntimeException(RuntimeException ex) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        apiResponse.setData(null);

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<ApiResponse<String>> handlerApiException(ApiException ex) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setCode(ex.getErrorCode().getCode());
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setData(null);

        return ResponseEntity.status(ex.getErrorCode().getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<String>> handlerAccessDeniedException(AccessDeniedException ex) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED_EXCEPTION;
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setData(null);

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<String>> handlingValidation(MethodArgumentNotValidException ex) {
        String enumKey = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation =
                    ex.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

        } catch (IllegalArgumentException exception) {

            System.out.println("check exception " + exception.getMessage());
        }

        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(
                Objects.nonNull(attributes)
                        ? mapAttribute(errorCode.getMessage(), attributes)
                        : errorCode.getMessage());
        apiResponse.setData(null);

        return ResponseEntity.badRequest().body(apiResponse);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minVal = String.valueOf(attributes.get(MIN_DOB_ATTRIBUTE));
        return message.replace("{" + MIN_DOB_ATTRIBUTE + "}", minVal);
    }
}
