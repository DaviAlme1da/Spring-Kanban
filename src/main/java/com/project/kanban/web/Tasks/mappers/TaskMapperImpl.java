package com.project.kanban.web.Tasks.mappers;

import org.springframework.stereotype.Component;

import com.project.kanban.core.exceptions.ProjectsNotFoundException;
import com.project.kanban.core.models.Task;
import com.project.kanban.core.repositories.ProjectsRepository;
import com.project.kanban.web.Tasks.dtos.TaskDetails;
import com.project.kanban.web.Tasks.dtos.TaskForm;
import com.project.kanban.web.Tasks.dtos.TaskListem;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskMapperImpl implements TaskMapper {

    private final ProjectsRepository projectsRepository;

    @Override
    public TaskListem toTaskListem(Task task) {
        return TaskListem.builder()
            .idTask(task.getId())
            .title(task.getTitle())
            .status(task.getStatus())
            .idProject(task.getProject().getId())
            .build();
    }

    @Override
    public TaskDetails toTaskDetails(Task task) {
        return TaskDetails.builder()
            .idTask(task.getId())
            .idProject(task.getProject().getId())
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .build();
    }

    @Override
    public Task toTask(TaskForm taskForm) {
        var projetct = projectsRepository.findById(taskForm.getProjectId())
        .orElseThrow(ProjectsNotFoundException::new);

        return Task.builder()
        .title(taskForm.getTitle())
        .description(taskForm.getDescription())
        .status(taskForm.getStatus())
        .project(projetct)
        .build();
    }

    @Override
    public TaskForm toTaskForm(Task task) {
        return TaskForm.builder()
                .title(task.getTitle())
                .status(task.getStatus())
                .description(task.getDescription())
                .projectId(task.getProject().getId())
                .build();
    }
    
}
