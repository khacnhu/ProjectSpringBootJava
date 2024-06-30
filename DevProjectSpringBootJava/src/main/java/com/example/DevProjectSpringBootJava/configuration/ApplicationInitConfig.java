package com.example.DevProjectSpringBootJava.configuration;


import com.example.DevProjectSpringBootJava.entity.User;
import com.example.DevProjectSpringBootJava.enums.Role;
import com.example.DevProjectSpringBootJava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {

                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());

                User user = User.builder()
                        .username("admin").
                        password(passwordEncoder.encode("admin"))
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
