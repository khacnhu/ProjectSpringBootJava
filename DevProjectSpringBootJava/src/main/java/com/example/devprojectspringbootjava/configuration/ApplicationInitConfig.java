package com.example.devprojectspringbootjava.configuration;

import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.devprojectspringbootjava.entity.User;
import com.example.devprojectspringbootjava.enums.Role;
import com.example.devprojectspringbootjava.repository.UserRepository;

@Configuration
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {

                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .firstName("ad")
                        .lastName("min")
                        .dob(LocalDate.parse("2000-02-13"))
                        //                        .roles(roles)
                        .build();

                userRepository.save(user);
            }
        };
    }
}
