package com.project.kanban.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.kanban.core.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);
}
