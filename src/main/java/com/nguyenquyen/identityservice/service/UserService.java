package com.nguyenquyen.identityservice.service;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.request.UserUpdateRequest;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;
import com.nguyenquyen.identityservice.enums.ErrorCode;
import com.nguyenquyen.identityservice.exception.AppException;
import com.nguyenquyen.identityservice.mapper.UserMapper;
import com.nguyenquyen.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserCreatationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
