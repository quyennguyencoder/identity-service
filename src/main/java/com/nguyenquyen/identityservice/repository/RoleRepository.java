package com.nguyenquyen.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenquyen.identityservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
