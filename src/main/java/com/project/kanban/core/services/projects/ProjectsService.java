package com.project.kanban.core.services.projects;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.kanban.core.repositories.ProjectsRepository;
import com.project.kanban.web.projects.dtos.ProjectsListem;
import com.project.kanban.web.projects.mappers.ProjectsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectsService {
    
    private final ProjectsRepository projectsRepository;
    private final ProjectsMapper projectsMapper;

    public List<ProjectsListem> findAll(){
        return projectsRepository.findAll()
                .stream()
                .map(projectsMapper::toProjectsListem)
                .toList();
    }
}
