package com.project.kanban.web.Tasks.mappers;

import com.project.kanban.core.models.Task;
import com.project.kanban.web.Tasks.dtos.TaskDetails;
import com.project.kanban.web.Tasks.dtos.TaskForm;
import com.project.kanban.web.Tasks.dtos.TaskListem;

public interface TaskMapper {
    
    TaskListem toTaskListem(Task task);

    TaskDetails toTaskDetails(Task task);

    TaskForm toTaskForm(Task task);

    Task toTask(TaskForm taskForm);
}
