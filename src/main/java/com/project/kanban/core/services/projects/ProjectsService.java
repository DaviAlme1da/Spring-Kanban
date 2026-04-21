package com.project.kanban.core.services.projects;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.project.kanban.core.auth.AuthService;
import com.project.kanban.core.enums.Role;
import com.project.kanban.core.exceptions.ProjectsNotFoundException;
import com.project.kanban.core.models.Projects;
import com.project.kanban.core.repositories.ProjectsRepository;
import com.project.kanban.web.projects.dtos.ProjectsDetails;
import com.project.kanban.web.projects.dtos.ProjectsForm;
import com.project.kanban.web.projects.dtos.ProjectsListem;
import com.project.kanban.web.projects.mappers.ProjectsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final ProjectsMapper projectsMapper;
    private final AuthService authService;

    public List<ProjectsListem> findAll() {
        var userLogado = authService.getUserLogado();
        boolean isAdmin = userLogado.getRole() == Role.ROLE_ADMIN;

        if (isAdmin) {
            return projectsRepository.findAllWithOwner()
                    .stream()
                    .map(projectsMapper::toProjectsListem)
                    .toList();
        }
        return projectsRepository.findByOwnerId(userLogado.getId())
                .stream()
                .map(projectsMapper::toProjectsListem)
                .toList();
    }

    public Projects save(ProjectsForm projectsForm) {
        projectsForm.setStartDate(LocalDate.now());
        Long idOwner = authService.getUserLogado().getId();
        projectsForm.setIdOwner(idOwner);
        var project = projectsMapper.toProjects(projectsForm);

        return projectsRepository.save(project);
    }

    public ProjectsForm update(Long id) {
        var project = projectsRepository.findByIdWithOwner(id)
                .orElseThrow(ProjectsNotFoundException::new);

        checkOwnershipOrAdmin(project);

        return projectsMapper.toProjectsForm(project);
    }

    public Projects update(Long id, ProjectsForm projectsForm) {
        var projectUpdate = projectsRepository.findByIdWithOwner(id)
                .orElseThrow(ProjectsNotFoundException::new);

        checkOwnershipOrAdmin(projectUpdate);

        projectUpdate.setName(projectsForm.getName());
        projectUpdate.setDescription(projectsForm.getDescription());
        return projectsRepository.save(projectUpdate);
    }

    public void delete(Long id) {
        var project = projectsRepository.findByIdWithOwner(id)
                .orElseThrow(ProjectsNotFoundException::new);

        checkOwnershipOrAdmin(project);

        projectsRepository.delete(project);
    }

    public ProjectsDetails details(Long id) {
        var project = projectsRepository.findByIdWithOwner(id)
                .orElseThrow(ProjectsNotFoundException::new);

        checkOwnershipOrAdmin(project);

        return projectsMapper.toProjectsDetails(project);
    }

    public void checkOwnershipOrAdmin(Projects project) {
        var userLogado = authService.getUserLogado();
        boolean isAdmin = userLogado.getRole() == Role.ROLE_ADMIN;
        boolean isOwner = project.getOwner().getId().equals(userLogado.getId());

        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("Acesso negado");
        }
    }
}
