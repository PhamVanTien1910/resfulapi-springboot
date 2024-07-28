package com.springboot.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.entity.RoleEntity;
import com.springboot.entity.UserEntity;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationinitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {

                RoleEntity adminRole = roleRepository.save(RoleEntity.builder()
                        .name("ADMIN")
                        .description("Admin role")
                        .build());

                Set<RoleEntity> roles = new HashSet<>();
                roles.add(adminRole);

                UserEntity user = UserEntity.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been create with default password: admin, 1");
            }
        };
    }
}
