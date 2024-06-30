package com.example.DevProjectSpringBootJava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class InvalidatedToken {

    @Id
    private String id;

    Date expiryTime;

}
