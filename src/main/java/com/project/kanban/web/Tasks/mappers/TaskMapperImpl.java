package com.project.kanban.web.Tasks.mappers;

import org.springframework.stereotype.Component;

import com.project.kanban.core.models.Task;
import com.project.kanban.web.Tasks.dtos.TaskDetails;
import com.project.kanban.web.Tasks.dtos.TaskListem;

@Component
public class TaskMapperImpl implements TaskMapper {

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
            .title(task.getTitle())
            .description(task.getDescription())
            .status(task.getStatus())
            .build();
    }
    
}
