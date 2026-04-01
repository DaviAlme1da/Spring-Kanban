package com.project.kanban.core.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.kanban.core.models.User;

@Service
public class AuthService {
    
     public User getUserLogado() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

}
