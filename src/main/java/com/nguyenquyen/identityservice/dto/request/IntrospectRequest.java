package com.nguyenquyen.identityservice.dto.request;

import lombok.Data;

@Data
public class IntrospectRequest {
    private String token;
}
