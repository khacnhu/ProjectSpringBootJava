package com.example.devprojectspringbootjava.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.devprojectspringbootjava.dto.request.UserCreationRequest;
import com.example.devprojectspringbootjava.dto.request.UserUpdatedRequest;
import com.example.devprojectspringbootjava.dto.response.UserResponse;
import com.example.devprojectspringbootjava.entity.User;
import com.example.devprojectspringbootjava.enums.Role;
import com.example.devprojectspringbootjava.exception.ApiException;
import com.example.devprojectspringbootjava.exception.ErrorCode;
import com.example.devprojectspringbootjava.mapper.ToMapperUser;
import com.example.devprojectspringbootjava.repository.RoleRepository;
import com.example.devprojectspringbootjava.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserResponse createRequest(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new ApiException(ErrorCode.USER_EXIST);

        User user = ToMapperUser.toCreateUser(userCreationRequest);
        PasswordEncoder hashPass = new BCryptPasswordEncoder(10);
        user.setPassword(hashPass.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        return ToMapperUser.toUserMapUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('UPDATE_DATA')")
    public List<UserResponse> getALlUser() {
        List<User> listUser = userRepository.findAll();

        return listUser.stream().map(ToMapperUser::toUserMapUserResponse).toList();
    }

    @PreAuthorize("hasRole('USER')")
    public UserResponse getFirstUser(String userId) {
        return ToMapperUser.toUserMapUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user is not found with id")));
    }

    public UserResponse updateUser(String userId, UserUpdatedRequest userUpdatedRequest) {
        User user = ToMapperUser.toUpdatedUser(
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user is not found with id")),
                userUpdatedRequest);

        user.setPassword(passwordEncoder.encode(userUpdatedRequest.getPassword()));

        var roles = roleRepository.findAllById(userUpdatedRequest.getRoles());
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);
        return ToMapperUser.toUserMapUserResponse(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo() {

        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXISTED));

        return ToMapperUser.toUserMapUserResponse(user);
    }
}
