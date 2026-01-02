package com.nguyenquyen.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenquyen.identityservice.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {}
