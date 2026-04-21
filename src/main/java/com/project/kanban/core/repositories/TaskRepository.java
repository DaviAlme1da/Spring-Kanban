package com.project.kanban.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.kanban.core.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Busca tasks com projeto em 1 query
    @Query("SELECT t FROM Task t JOIN FETCH t.project WHERE t.project.id = :projectId")
    List<Task> findByProjectId(@Param("projectId") Long projectId);
}
