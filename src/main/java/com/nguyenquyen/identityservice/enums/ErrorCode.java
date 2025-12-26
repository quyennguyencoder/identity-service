package com.nguyenquyen.identityservice.enums;


import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User already existed"),
    USER_NOT_FOUND(1002, "User not found"),
    USERNAME_INVALID(1003, "Username must be between 3 and 15 characters"),
    PASSWORD_INVALID(1004, "Password must be between 3 and 15 characters"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error");



    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
