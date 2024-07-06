package com.example.devprojectspringbootjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devprojectspringbootjava.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
