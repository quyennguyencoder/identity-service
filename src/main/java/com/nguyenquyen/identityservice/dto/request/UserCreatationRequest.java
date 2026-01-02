package com.nguyenquyen.identityservice.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

import com.nguyenquyen.identityservice.validator.DobConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatationRequest {
    @Size(min = 3, max = 15, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 3, max = 15, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;
    private String lastName;

    @DobConstraint(min = 2, message = "INVALID_DOB")
    private LocalDate dob;
}
