package com.example.devprojectspringbootjava.mapper;

import com.example.devprojectspringbootjava.dto.request.UserCreationRequest;
import com.example.devprojectspringbootjava.dto.request.UserUpdatedRequest;
import com.example.devprojectspringbootjava.dto.response.UserResponse;
import com.example.devprojectspringbootjava.entity.User;

public class ToMapperUser {

    public static User toCreateUser(UserCreationRequest userCreationRequest) {
        User user = new User();

        user.setUsername(userCreationRequest.getUsername());
        user.setPassword(userCreationRequest.getPassword());
        user.setFirstName(userCreationRequest.getFirstName());
        user.setLastName(userCreationRequest.getLastName());
        user.setDob(userCreationRequest.getDob());

        return user;
    }

    public static User toUpdatedUser(User user, UserUpdatedRequest userUpdatedRequest) {

        user.setPassword(userUpdatedRequest.getPassword());
        user.setFirstName(userUpdatedRequest.getFirstName());
        user.setLastName(userUpdatedRequest.getLastName());
        user.setDob(userUpdatedRequest.getDob());

        return user;
    }

    public static UserResponse toUserMapUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDob(user.getDob());
        userResponse.setRoles(user.getRoles());

        return userResponse;
    }
}
