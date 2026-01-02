package com.nguyenquyen.identityservice.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nguyenquyen.identityservice.dto.request.RoleRequest;
import com.nguyenquyen.identityservice.dto.response.RoleResponse;
import com.nguyenquyen.identityservice.entity.Permission;
import com.nguyenquyen.identityservice.entity.Role;
import com.nguyenquyen.identityservice.mapper.RoleMapper;
import com.nguyenquyen.identityservice.repository.PermissionRepository;
import com.nguyenquyen.identityservice.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        Role savedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(savedRole);
    }

    public List<RoleResponse> getAlls() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> roleMapper.toRoleResponse(role)).toList();
    }

    public void delete(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
