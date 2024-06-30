package com.example.DevProjectSpringBootJava.mapper;

import com.example.DevProjectSpringBootJava.dto.request.UserCreationRequest;
import com.example.DevProjectSpringBootJava.dto.request.UserUpdatedRequest;
import com.example.DevProjectSpringBootJava.dto.response.UserResponse;
import com.example.DevProjectSpringBootJava.entity.Role;
import com.example.DevProjectSpringBootJava.entity.User;

import java.util.Set;

public class toMapperUser {

    public static User toCreateUser(UserCreationRequest userCreationRequest) {
        User user = new User();

        user.setUsername(userCreationRequest.getUsername());
        user.setPassword(userCreationRequest.getPassword());
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setDob(userCreationRequest.getDob());

        return user;
    }

    public static User toUpdatedUser (User user, UserUpdatedRequest userUpdatedRequest) {

        user.setPassword(userUpdatedRequest.getPassword());
        user.setFirstName(userUpdatedRequest.getFirstName());
        user.setLastName(userUpdatedRequest.getLastName());
        user.setDob(userUpdatedRequest.getDob());

        return user;
    }

    public static UserResponse toUserMapUserResponse (User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
//        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDob(user.getDob());
        userResponse.setRoles(user.getRoles());

        return userResponse;
    }



}
