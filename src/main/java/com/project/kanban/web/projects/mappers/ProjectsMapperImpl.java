package com.project.kanban.web.projects.mappers;

import org.springframework.stereotype.Component;

import com.project.kanban.core.exceptions.UserNotFoundException;
import com.project.kanban.core.models.Projects;
import com.project.kanban.core.repositories.UserRepository;
import com.project.kanban.web.projects.dtos.ProjectsForm;
import com.project.kanban.web.projects.dtos.ProjectsListem;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class ProjectsMapperImpl implements ProjectsMapper{

    private final UserRepository userRepository;

    @Override
    public Projects toProjects(ProjectsForm projectsForm) {
        var owner = userRepository.findById(projectsForm.getIdOwner())
                    .orElseThrow(UserNotFoundException::new);
       
        return Projects.builder()
                .name(projectsForm.getName())
                .description(projectsForm.getDescription())
                .startDate(projectsForm.getStartDate())
                .owner(owner)
                .build();
    }

    @Override
    public ProjectsForm toProjectsForm(Projects projects) {
        return ProjectsForm.builder()
            .name(projects.getName())
            .description(projects.getDescription())
            .startDate(projects.getStartDate())
            .idOwner(projects.getOwner().getId())
            .build();
    }

    @Override
    public ProjectsListem toProjectsListem(Projects projects) {
        return ProjectsListem.builder()
            .id(projects.getId())
            .name(projects.getName())
            .startDate(projects.getStartDate())
            .ownerName(projects.getOwner().getName())
            .build();
    }
    
}
