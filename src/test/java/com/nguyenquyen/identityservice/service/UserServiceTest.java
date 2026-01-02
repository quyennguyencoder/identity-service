package com.nguyenquyen.identityservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.nguyenquyen.identityservice.dto.request.UserCreatationRequest;
import com.nguyenquyen.identityservice.dto.response.UserResponse;
import com.nguyenquyen.identityservice.entity.User;
import com.nguyenquyen.identityservice.exception.AppException;
import com.nguyenquyen.identityservice.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreatationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;
    private User user;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2015, 1, 1);
        request = UserCreatationRequest.builder()
                .username("testuser")
                .password("testpassword")
                .firstName("testuser")
                .lastName("testuser")
                .dob(dob)
                .build();
        userResponse = UserResponse.builder()
                .id("1")
                .username("testuser")
                .firstName("testuser")
                .lastName("testuser")
                .dob(dob)
                .build();
        user = User.builder()
                .id("1")
                .username("testuser")
                .firstName("testuser")
                .lastName("testuser")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // given
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        // when
        var response = userService.createUser(request);
        // then
        Assertions.assertThat(response.getId()).isEqualTo("1");
        Assertions.assertThat(response.getUsername()).isEqualTo("testuser");
    }

    @Test
    void createUser_userExisted_fail() {
        // given
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);

        // when
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }

    @Test
    @WithMockUser(username = "testuser")
    void getMyInfor_valid_success() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        var response = userService.getMyInfo();
        assertThat(response.getUsername()).isEqualTo("testuser");
        assertThat(response.getId()).isEqualTo("1");
    }

    @Test
    @WithMockUser(username = "testuser")
    void getMyInfor_userNotFound_fail() {
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());
        assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }
}
