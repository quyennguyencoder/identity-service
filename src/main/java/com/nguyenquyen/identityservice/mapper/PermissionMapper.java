package com.nguyenquyen.identityservice.mapper;

import org.mapstruct.Mapper;

import com.nguyenquyen.identityservice.dto.request.PermissionRequest;
import com.nguyenquyen.identityservice.dto.response.PermissionResponse;
import com.nguyenquyen.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
