package com.example.devprojectspringbootjava.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.devprojectspringbootjava.dto.request.UserCreationRequest;
import com.example.devprojectspringbootjava.dto.request.UserUpdatedRequest;
import com.example.devprojectspringbootjava.dto.response.ApiResponse;
import com.example.devprojectspringbootjava.dto.response.UserResponse;
import com.example.devprojectspringbootjava.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>();
        userApiResponse.setCode(200);
        userApiResponse.setMessage("User create successfully");
        userApiResponse.setData(userService.createRequest(userCreationRequest));
        return userApiResponse;
    }

    @GetMapping
    public List<UserResponse> getAllUser() {
        return userService.getALlUser();
    }

    @GetMapping("/{userId}")
    public UserResponse getDetailUser(@PathVariable String userId) {
        return userService.getFirstUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updatedUser(@PathVariable String userId, @RequestBody UserUpdatedRequest userUpdatedRequest) {
        return userService.updateUser(userId, userUpdatedRequest);
    }

    @DeleteMapping("/{userId}")
    public String deletedUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "DELETE SUCCESSFULLY";
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        UserResponse userResponse = userService.getMyInfo();

        apiResponse.setCode(200);
        apiResponse.setMessage("Detail User Login successfully");
        apiResponse.setData(userResponse);
        return apiResponse;
    }
}
