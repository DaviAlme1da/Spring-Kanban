package com.project.kanban.core.services.projects;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.kanban.core.exceptions.ProjectsNotFoundException;
import com.project.kanban.core.models.Projects;
import com.project.kanban.core.repositories.ProjectsRepository;
import com.project.kanban.web.projects.dtos.ProjectsForm;
import com.project.kanban.web.projects.dtos.ProjectsListem;
import com.project.kanban.web.projects.mappers.ProjectsMapper;

import lombok.RequiredArgsConstructor;
import lombok.var;

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


    public Projects save(ProjectsForm projectsForm){
        projectsForm.setStartDate(LocalDate.now());
        projectsForm.setIdOwner(1l);
        var project = projectsMapper.toProjects(projectsForm);

        return projectsRepository.save(project);
    }

    public ProjectsForm update(Long id){
        var projectsForm = projectsRepository.findById(id)
                        .map(projectsMapper::toProjectsForm)
                        .orElseThrow(ProjectsNotFoundException::new);
        return projectsForm;
    }

    public Projects update(Long id, ProjectsForm projectsForm){
        var projectUpdate = projectsRepository.findById(id)
            .orElseThrow(ProjectsNotFoundException::new);
        
        projectUpdate.setName(projectsForm.getName());
        projectUpdate.setDescription(projectsForm.getDescription());

        return projectsRepository.save(projectUpdate);
    }

    public void delete(Long id){
        var projectDelete = projectsRepository.findById(id)
            .orElseThrow(ProjectsNotFoundException::new);
        
         projectsRepository.delete(projectDelete);
    }
}
