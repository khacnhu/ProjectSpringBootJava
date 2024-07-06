package com.example.devprojectspringbootjava.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

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
