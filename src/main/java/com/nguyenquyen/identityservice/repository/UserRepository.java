package com.nguyenquyen.identityservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenquyen.identityservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
