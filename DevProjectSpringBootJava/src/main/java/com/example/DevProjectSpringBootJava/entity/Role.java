package com.example.DevProjectSpringBootJava.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Role {
    @Id
    private String name;

    private String description;

    @ManyToMany
    Set<Permission> permissions;

}
