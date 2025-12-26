package com.nguyenquyen.identityservice.repository;

import com.nguyenquyen.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsername(String username);
}
