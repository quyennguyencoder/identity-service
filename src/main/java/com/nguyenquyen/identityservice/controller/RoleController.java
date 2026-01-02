package com.nguyenquyen.identityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nguyenquyen.identityservice.dto.request.RoleRequest;
import com.nguyenquyen.identityservice.dto.response.ApiResponse;
import com.nguyenquyen.identityservice.dto.response.RoleResponse;
import com.nguyenquyen.identityservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAlls() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAlls())
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponse<Void> delete(@PathVariable String roleName) {
        roleService.delete(roleName);
        return ApiResponse.<Void>builder().build();
    }
}
