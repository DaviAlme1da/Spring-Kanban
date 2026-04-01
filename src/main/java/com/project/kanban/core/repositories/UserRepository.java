package com.project.kanban.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kanban.core.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByName(String name);
}
