package com.nguyenquyen.identityservice.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nguyenquyen.identityservice.entity.User;
import com.nguyenquyen.identityservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "spring",
        value = "datasource.driver-class-name",
        havingValue = "com.mysql.cj.jdbc.Driver")
public class ApplicationInitConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Bean
    ApplicationRunner applicationRunner() {
        // spotless:off
        log.info("Application config: Checking for admin user...");
        // spotless:on
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                //                Set<String> roles = new HashSet<>();
                //                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
                log.info("Admin user created with username: admin and password: admin");
            }
        };
    }
}
