package com.example.DevProjectSpringBootJava.controller;


import com.example.DevProjectSpringBootJava.Service.UserService;
import com.example.DevProjectSpringBootJava.dto.request.UserCreationRequest;
import com.example.DevProjectSpringBootJava.dto.request.UserUpdatedRequest;
import com.example.DevProjectSpringBootJava.dto.response.ApiResponse;
import com.example.DevProjectSpringBootJava.dto.response.UserResponse;
import com.example.DevProjectSpringBootJava.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

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
