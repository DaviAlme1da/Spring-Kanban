package com.project.kanban.core.services.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.kanban.core.enums.Status;
import com.project.kanban.core.exceptions.TasksNotFoundException;
import com.project.kanban.core.repositories.TaskRepository;
import com.project.kanban.web.Tasks.dtos.TaskDetails;
import com.project.kanban.web.Tasks.dtos.TaskForm;
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

    public void save(TaskForm taskForm, Long idProject){
        taskForm.setProjectId(idProject);
        taskForm.setStatus(Status.TODO);
        var task = taskMapper.toTask(taskForm);

        taskRepository.save(task);
    }

    public TaskForm update(Long id){
        return taskRepository.findById(id)
        .map(taskMapper::toTaskForm)
        .orElseThrow(TasksNotFoundException::new)
        ;
    }

    public void update(Long id, TaskForm taskForm){
        var taskUpdate = taskRepository.findById(id)
            .orElseThrow(TasksNotFoundException::new);
        
        taskUpdate.setTitle(taskForm.getTitle());
        taskUpdate.setDescription(taskForm.getDescription());

        taskRepository.save(taskUpdate);
    }

    public void moveToNextStatus(Long id){
        var taskUpdate = taskRepository.findById(id)
            .orElseThrow(TasksNotFoundException::new);
        
        if(taskUpdate.getStatus().equals(Status.TODO)){
            taskUpdate.setStatus(Status.DOING);
        }else if (taskUpdate.getStatus().equals(Status.DOING)){
            taskUpdate.setStatus(Status.DONE);
        }

        taskRepository.save(taskUpdate);
    }

    public void moveToPreviousStatus(Long id){
        var taskUpdate = taskRepository.findById(id)
            .orElseThrow(TasksNotFoundException::new);
        
        if(taskUpdate.getStatus().equals(Status.DONE)){
            taskUpdate.setStatus(Status.DOING);
        }else if (taskUpdate.getStatus().equals(Status.DOING)){
            taskUpdate.setStatus(Status.TODO);
        }

        taskRepository.save(taskUpdate);
    }

    public void delete(Long id){
        var task = taskRepository.findById(id)
            .orElseThrow(TasksNotFoundException::new);
        ;

        taskRepository.delete(task);
    }

    public TaskDetails details(Long id){
        return taskRepository.findById(id)
            .map(taskMapper::toTaskDetails)
             .orElseThrow(TasksNotFoundException::new);
    }

}
