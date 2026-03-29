package com.project.kanban.web.projects.mappers;

import com.project.kanban.core.models.Projects;
import com.project.kanban.web.projects.dtos.ProjectsForm;
import com.project.kanban.web.projects.dtos.ProjectsListem;

public interface ProjectsMapper {
    
    Projects toProjects(ProjectsForm projectsForm);

    ProjectsForm toProjectsForm(Projects projects);

    ProjectsListem toProjectsListem(Projects projects);
}
