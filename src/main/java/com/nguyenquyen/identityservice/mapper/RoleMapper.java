package com.nguyenquyen.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nguyenquyen.identityservice.dto.request.RoleRequest;
import com.nguyenquyen.identityservice.dto.response.RoleResponse;
import com.nguyenquyen.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
