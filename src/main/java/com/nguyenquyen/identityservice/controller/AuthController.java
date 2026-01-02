package com.nguyenquyen.identityservice.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.*;

import com.nguyenquyen.identityservice.dto.request.IntrospectRequest;
import com.nguyenquyen.identityservice.dto.request.LoginRequest;
import com.nguyenquyen.identityservice.dto.request.LogoutRequest;
import com.nguyenquyen.identityservice.dto.request.RefreshRequest;
import com.nguyenquyen.identityservice.dto.response.ApiResponse;
import com.nguyenquyen.identityservice.dto.response.AuthResponse;
import com.nguyenquyen.identityservice.dto.response.IntrospectResponse;
import com.nguyenquyen.identityservice.service.AuthService;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authenticatijonService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.<AuthResponse>builder()
                .result(authenticatijonService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspectToken(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticatijonService.introspectToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticatijonService.logout(request);
        return ApiResponse.builder().build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> login(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthResponse>builder()
                .result(authenticatijonService.refreshToken(request))
                .build();
    }
}
