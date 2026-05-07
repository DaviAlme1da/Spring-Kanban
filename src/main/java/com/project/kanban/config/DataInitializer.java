package com.project.kanban.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.kanban.core.enums.Role;
import com.project.kanban.core.models.User;
import com.project.kanban.core.repositories.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            String adminName = "admin";

            boolean existe = userRepository
                    .findByName(adminName)
                    .isPresent();

            if (!existe) {

                User admin = User.builder()
                        .name("admin")
                        .password(
                                passwordEncoder.encode("123456")
                        )
                        .role(Role.ROLE_ADMIN)
                        .build();

                userRepository.save(admin);
            }
        };
    }
}
