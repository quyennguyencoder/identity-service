package com.nguyenquyen.identityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nguyenquyen.identityservice.dto.request.PermissionRequest;
import com.nguyenquyen.identityservice.dto.response.ApiResponse;
import com.nguyenquyen.identityservice.dto.response.PermissionResponse;
import com.nguyenquyen.identityservice.service.PermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAlls() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAlls())
                .build();
    }

    @DeleteMapping("/{permissionName}")
    public ApiResponse<Void> deletePermission(@PathVariable String permissionName) {
        permissionService.delete(permissionName);
        return ApiResponse.<Void>builder().build();
    }
}
