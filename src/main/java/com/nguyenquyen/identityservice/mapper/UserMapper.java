package com.nguyenquyen.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.request.UserUpdateRequest;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreatationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
