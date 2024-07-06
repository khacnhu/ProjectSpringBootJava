package com.example.devprojectspringbootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devprojectspringbootjava.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
