package com.example.DevProjectSpringBootJava.repository;

import com.example.DevProjectSpringBootJava.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
