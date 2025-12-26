package com.nguyenquyen.identityservice.mapper;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.request.UserUpdateRequest;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreatationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}
