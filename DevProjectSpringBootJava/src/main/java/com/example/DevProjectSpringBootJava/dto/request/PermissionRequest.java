package com.example.DevProjectSpringBootJava.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequest {

    private String name;
    private String description;
}
