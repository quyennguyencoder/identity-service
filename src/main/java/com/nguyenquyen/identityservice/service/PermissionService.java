package com.nguyenquyen.identityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nguyenquyen.identityservice.dto.request.PermissionRequest;
import com.nguyenquyen.identityservice.dto.response.PermissionResponse;
import com.nguyenquyen.identityservice.entity.Permission;
import com.nguyenquyen.identityservice.mapper.PermissionMapper;
import com.nguyenquyen.identityservice.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        Permission savedPermission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(savedPermission);
    }

    public List<PermissionResponse> getAlls() {
        List<Permission> permissions = permissionRepository.findAll();
        List<PermissionResponse> response = permissions.stream()
                .map(permission -> permissionMapper.toPermissionResponse(permission))
                .toList();
        return response;
    }

    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
