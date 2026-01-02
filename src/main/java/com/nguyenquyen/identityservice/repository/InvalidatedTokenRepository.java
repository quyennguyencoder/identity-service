package com.nguyenquyen.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyenquyen.identityservice.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
