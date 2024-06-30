package com.example.DevProjectSpringBootJava.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    @ManyToMany
    private Set<Role> roles;

}

