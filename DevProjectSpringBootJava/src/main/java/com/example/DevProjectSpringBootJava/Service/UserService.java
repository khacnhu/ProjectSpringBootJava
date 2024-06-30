package com.example.DevProjectSpringBootJava.Service;

import com.example.DevProjectSpringBootJava.dto.request.UserCreationRequest;
import com.example.DevProjectSpringBootJava.dto.request.UserUpdatedRequest;
import com.example.DevProjectSpringBootJava.dto.response.RoleResponse;
import com.example.DevProjectSpringBootJava.dto.response.UserResponse;
import com.example.DevProjectSpringBootJava.entity.User;
import com.example.DevProjectSpringBootJava.enums.Role;
import com.example.DevProjectSpringBootJava.exception.ApiException;
import com.example.DevProjectSpringBootJava.exception.ErrorCode;
import com.example.DevProjectSpringBootJava.mapper.toMapperUser;
import com.example.DevProjectSpringBootJava.repository.RoleRepository;
import com.example.DevProjectSpringBootJava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public UserResponse createRequest(UserCreationRequest userCreationRequest) {
        if (userRepository.existsByUsername(userCreationRequest.getUsername()))
            throw new ApiException(ErrorCode.USER_EXIST);

        User user = toMapperUser.toCreateUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(new HashSet<>(roles));



        return toMapperUser.toUserMapUserResponse(userRepository.save(user));


    }


    @PreAuthorize("hasRole('UPDATE_DATA')")
    public List<UserResponse> getALlUser() {
        List<User> listUser = userRepository.findAll();

        return listUser.stream().map(
                toMapperUser::toUserMapUserResponse
        ).toList();
    }

    @PreAuthorize("hasRole('USER')")
    public UserResponse getFirstUser(String userId) {
        return toMapperUser.toUserMapUserResponse(userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("user is not found with id")
        ));
    }

    public UserResponse updateUser(String userId, UserUpdatedRequest userUpdatedRequest) {
        User user = toMapperUser.toUpdatedUser(userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("user is not found with id")), userUpdatedRequest);


        user.setPassword(passwordEncoder.encode(userUpdatedRequest.getPassword()));

        var roles = roleRepository.findAllById(userUpdatedRequest.getRoles());
        System.out.println("check roles " + roles);
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);
        return toMapperUser.toUserMapUserResponse(user);

    }

    public void deleteUser (String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo() {

        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new ApiException(ErrorCode.USER_NOT_EXISTED)
        );

        return toMapperUser.toUserMapUserResponse(user);


    }



}
