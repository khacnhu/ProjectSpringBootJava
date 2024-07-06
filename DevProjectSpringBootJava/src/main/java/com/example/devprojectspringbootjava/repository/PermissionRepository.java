package com.example.devprojectspringbootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devprojectspringbootjava.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
