package com.project.kanban.web.Tasks.dtos;

import com.project.kanban.core.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetails {

    private Long idTask;
    
    private Long idProject;
    
    private String title;

    private String description;

    private Status status;
}
