package com.project.kanban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return  http.authorizeHttpRequests(customizer -> customizer
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/projects/**").hasAnyRole("COMMONUSER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(customizer -> customizer
                .loginPage("/auth/login")
                .defaultSuccessUrl("/projects", true)
                .permitAll()
            )
            .logout(customizer -> customizer
                .logoutSuccessUrl("/auth/login")
                .permitAll()
            )

        
            .build();
    }
}
