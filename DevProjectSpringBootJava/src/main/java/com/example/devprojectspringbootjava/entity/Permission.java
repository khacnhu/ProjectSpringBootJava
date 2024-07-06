package com.example.devprojectspringbootjava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
public class Permission {

    @Id
    String name;

    String description;
}
