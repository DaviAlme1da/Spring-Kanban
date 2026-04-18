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
public class TaskForm {
    
    private String title;

    private Status status;

    private String description;

    private Long projectId;
}
