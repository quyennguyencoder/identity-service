package com.nguyenquyen.identityservice.controller;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.request.UserUpdateRequest;
import com.nguyenquyen.identityservice.dto.response.ApiResponse;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;
import com.nguyenquyen.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreatationRequest request) {
        User user = userService.createUser(request);
        return ApiResponse.<User>builder()
                .code(201)
                .result(user)
                .build();
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId,
                            @RequestBody UserUpdateRequest request
    ){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted.";
    }
}
