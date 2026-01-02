package com.nguyenquyen.identityservice.service;

import java.util.HashSet;
import java.util.List;

import com.nguyenquyen.identityservice.entity.Role;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.request.UserUpdateRequest;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;
import com.nguyenquyen.identityservice.enums.ErrorCode;
import com.nguyenquyen.identityservice.exception.AppException;
import com.nguyenquyen.identityservice.mapper.UserMapper;
import com.nguyenquyen.identityservice.repository.RoleRepository;
import com.nguyenquyen.identityservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserResponse createUser(UserCreatationRequest request) {
        log.info("UserService: Create User");
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(com.nguyenquyen.identityservice.enums.Role.USER.name()).ifPresent(role -> {
            roles.add(role);
        });

        user.setRoles(roles);
        try {
            User savedUser = userRepository.save(user);
            return userMapper.toUserResponse(savedUser);
        }catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.USER_EXISTED);
        }


    }

    public List<UserResponse> getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username {}", authentication.getName());
        authentication.getAuthorities().forEach(authority -> {
            log.info("authority {}", authority.getAuthority());
        });

        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.toUserResponse(user)).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        log.info("Getting user with id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
}
