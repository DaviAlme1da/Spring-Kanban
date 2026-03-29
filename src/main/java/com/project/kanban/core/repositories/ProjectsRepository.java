package com.project.kanban.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kanban.core.models.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    
}
