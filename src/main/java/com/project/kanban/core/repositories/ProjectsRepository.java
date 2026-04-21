package com.project.kanban.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.kanban.core.models.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

    @Query("SELECT p FROM Projects p JOIN FETCH p.owner")
    List<Projects> findAllWithOwner();

    // mesmo nome que você já usa — só adiciona o JOIN FETCH na query
    @Query("SELECT p FROM Projects p JOIN FETCH p.owner WHERE p.owner.id = :ownerId")
    List<Projects> findByOwnerId(@Param("ownerId") Long ownerId);

    @Query("SELECT p FROM Projects p JOIN FETCH p.owner WHERE p.id = :id")
    Optional<Projects> findByIdWithOwner(@Param("id") Long id);
}
