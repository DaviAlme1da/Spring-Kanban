package com.project.kanban.core.services.tasks;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.kanban.core.enums.Status;
import com.project.kanban.core.exceptions.ProjectsNotFoundException;
import com.project.kanban.core.exceptions.TasksNotFoundException;
import com.project.kanban.core.repositories.ProjectsRepository;
import com.project.kanban.core.repositories.TaskRepository;
import com.project.kanban.core.services.projects.ProjectsService;
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
    private final ProjectsRepository projectsRepository;
    private final ProjectsService projectsService;

    public List<TaskListem> index(Long idProject) {
        var project = projectsRepository.findById(idProject)
                .orElseThrow(ProjectsNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(project); 

        return taskRepository.findByProjectId(idProject)
                .stream()
                .map(taskMapper::toTaskListem)
                .toList();
    }

    public void save(TaskForm taskForm, Long idProject) {
        var project = projectsRepository.findById(idProject)
                .orElseThrow(ProjectsNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(project); 

        taskForm.setProjectId(idProject);
        taskForm.setStatus(Status.TODO);
        taskRepository.save(taskMapper.toTask(taskForm));
    }

    public TaskForm update(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        return taskMapper.toTaskForm(task);
    }

    public void update(Long id, TaskForm taskForm) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        task.setTitle(taskForm.getTitle());
        task.setDescription(taskForm.getDescription());
        taskRepository.save(task);
    }

    public void moveToNextStatus(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        if (task.getStatus().equals(Status.TODO)) {
            task.setStatus(Status.DOING);
        } else if (task.getStatus().equals(Status.DOING)) {
            task.setStatus(Status.DONE);
        }
        taskRepository.save(task);
    }

    public void moveToPreviousStatus(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        if (task.getStatus().equals(Status.DONE)) {
            task.setStatus(Status.DOING);
        } else if (task.getStatus().equals(Status.DOING)) {
            task.setStatus(Status.TODO);
        }
        taskRepository.save(task);
    }

    public void delete(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        taskRepository.delete(task);
    }

    public TaskDetails details(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(TasksNotFoundException::new);

        projectsService.checkOwnershipOrAdmin(task.getProject()); 

        return taskMapper.toTaskDetails(task);
    }
}