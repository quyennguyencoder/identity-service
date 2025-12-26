package com.nguyenquyen.identityservice.dto.request;

import com.nguyenquyen.identityservice.enums.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreatationRequest {
    @Size(min = 3 , max = 15, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 3, max = 15, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
