package com.project.kanban.core.services.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.kanban.core.exceptions.TasksNotFoundException;
import com.project.kanban.core.repositories.TaskRepository;
import com.project.kanban.web.Tasks.dtos.TaskDetails;
import com.project.kanban.web.Tasks.dtos.TaskListem;
import com.project.kanban.web.Tasks.mappers.TaskMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TasksService {
    
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    
    public List<TaskListem> index(Long idProject){
        return taskRepository.findByProjectId(idProject)
            .stream()
            .map(taskMapper::toTaskListem)
            .toList()
            ;
    }

    public TaskDetails details(Long id){
        return taskRepository.findById(id)
            .map(taskMapper::toTaskDetails)
             .orElseThrow(TasksNotFoundException::new);
    }

}
